package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.ISkuInfoSwitchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom-server <br>
 * Description: <br>
 * Date:2015-12-31下午2:17:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * forkjoin并行任务分配控制类
 */
public class ImportDistributeDataFJTask extends RecursiveTask<ImprotPromResultDTO>{
    
    private static final long serialVersionUID = -46220250989880427L;
    
    private static final String MODULE = ImportDistributeDataFJTask.class.getName();
    
    private IPromInfoSV promInfoSV = EcpFrameContextHolder.getBean("promInfoSV", IPromInfoSV.class);
    private IProm2SolrSV prom2SolrSV=  EcpFrameContextHolder.getBean("prom2SolrSV", IProm2SolrSV.class);    
    private IPromImportFileSV promImportFileSV=  EcpFrameContextHolder.getBean("promImportFileSV", IPromImportFileSV.class);
    private ISkuInfoSwitchSV skuInfoSwitchSV =  EcpFrameContextHolder.getBean("skuInfoSwitchSV", ISkuInfoSwitchSV.class);
    
    //一个任务单执行的数据量,根据测试调整
    private final int ONE_TASK_DEAL_NUMBER = 100;
    
    //秒  86400秒=24小时
    public static final int cacheSeconds = 86400;
    
    //计数器，计算器执行完，表示最后一个任务也跑完
    private CountDownLatch countDownLatch = null;

    //该任务所对应的促销
    private Long promId = null;
    //导入文件
    private String fileId = null;
    //需要处理的数据
    private List<List<Object>> datas;
    //重复数量
    private long repeatCount = 0;
    //总数
    private long totalCount = 0;
    
    private PromImportReqDTO promImportReqDTO = null;
    
    public ImportDistributeDataFJTask(String fileId, Long promId, PromImportReqDTO promImportReqDTO, List<List<Object>> datas)
    {
        this.fileId = fileId;
        this.promId = promId;
        this.promImportReqDTO = promImportReqDTO;
        this.datas = filterDatas(datas);
    }
    
    @Override
    protected ImprotPromResultDTO compute() {
        
        LogUtil.warn(MODULE, "主线程[ID："+Thread.currentThread().getId()+"]开始执行");
        
        ImprotPromResultDTO result = new ImprotPromResultDTO();
        //设置过滤结果重复数
        result.setRepeatCount(this.repeatCount);
        result.setTotalCount(this.totalCount);
        
        //需要拆分成多少个任务单数
        int needSplitCount = 1;
        //剩余多少任务单
        int leftCount = datas.size()%ONE_TASK_DEAL_NUMBER;
        needSplitCount = (leftCount == 0?datas.size()/ONE_TASK_DEAL_NUMBER:datas.size()/ONE_TASK_DEAL_NUMBER+1);
        //初始化计数器
        countDownLatch = new CountDownLatch(needSplitCount);
        
        //保存子任务
        List<ImportDistributeDataSubFJTask> subTasks = new ArrayList<ImportDistributeDataSubFJTask>(needSplitCount);
        
        //获得促销基础信息
        final PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promId);
 
        //拆分任务单
        for(int i=0; i<needSplitCount; i++)
        {
            ImportDistributeDataSubFJTask workTask = null;
            if(i == needSplitCount-1 && leftCount!=0)
            {
                
                workTask  = new ImportDistributeDataSubFJTask(i*ONE_TASK_DEAL_NUMBER, i*ONE_TASK_DEAL_NUMBER+leftCount, countDownLatch, datas);
            }else {
                workTask = new ImportDistributeDataSubFJTask(i*ONE_TASK_DEAL_NUMBER, (i+1)*ONE_TASK_DEAL_NUMBER, countDownLatch, datas);
            }
            workTask.setPromInfoDTO(promInfoDTO);
            workTask.setDatasFileId(this.fileId);
            workTask.setPromImportReqDTO(this.promImportReqDTO);
            subTasks.add(workTask);
            //提交到线程池队列中
            workTask.fork();
        }
        
        /*该注释也可打开，下面的for循环中的join也有阻塞的作用
        try {
            //等待所有子任务执行完毕，考虑设置超时
            countDownLatch.await();
        } catch (InterruptedException e) {
        }*/
        
        //所有任务执行完毕汇总子任务单处理结果
        for(ImportDistributeDataSubFJTask task : subTasks)
        {
            ImprotPromResultDTO subresult = task.join();
            result.setSuccessCount(subresult.getSuccessCount()+result.getSuccessCount());
            result.setErrorCount(subresult.getErrorCount()+result.getErrorCount());
        }
        LogUtil.info(MODULE, "所有子任务执行完毕，汇总结果："+result.toString());
        
        //通知solr
        CacheUtil.addItem(promImportReqDTO.getFileId(), "1", cacheSeconds);

        PromImportFileReqDTO promImportFileReqDTO=new PromImportFileReqDTO();
        promImportFileReqDTO.setCreateStaff(promImportReqDTO.getStaff().getId());
        promImportFileReqDTO.setFileId(promImportReqDTO.getFileId());
        promImportFileReqDTO.setFileName(promImportReqDTO.getFileName());
        promImportFileReqDTO.setImportDesc(promImportReqDTO.getImportDesc());
        promImportFileReqDTO.setImportType(promImportReqDTO.getImportType());
        
        promImportFileSV.save(promImportFileReqDTO);
        
        //solr消息 记录
        if(promInfoDTO!=null){
            Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
            ObjectCopyUtil.copyObjValue(promInfoDTO, prom2SolrReqDTO, "", false);
            prom2SolrReqDTO.setPromId(promInfoDTO.getId());
            prom2SolrSV.save(prom2SolrReqDTO);
        }
        
        return result;
    }

    //过滤掉datas中相同的数据
    private List<List<Object>> filterDatas(List<List<Object>> datas)
    {
        Map<String,Object> map = skuInfoSwitchSV.filterDatas(datas);
        List<List<Object>> resultDatas  = (List<List<Object>>) map.get("resultDatas");
        totalCount = Long.parseLong(""+map.get("totalCount"));
        repeatCount = Long.parseLong(""+map.get("repeatCount"));
        return resultDatas;
    }
}

