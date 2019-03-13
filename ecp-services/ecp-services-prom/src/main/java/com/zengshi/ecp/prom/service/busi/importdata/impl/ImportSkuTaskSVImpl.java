package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.concurrent.CountDownLatch;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-05 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class ImportSkuTaskSVImpl implements Runnable {

    private static final String MODULE = ImportSkuTaskSVImpl.class.getName();

    private final PromImportReqDTO promImportReqDTO;
    
    private final PromSku promSku;
    
    private final PromStockLimit promStockLimit;
    
    private final CountDownLatch countDownLatch ;
    
    private IPromInfoSV promInfoSV=  EcpFrameContextHolder.getBean("promInfoSV", IPromInfoSV.class);
    
    private IProm2SolrSV prom2SolrSV=  EcpFrameContextHolder.getBean("prom2SolrSV", IProm2SolrSV.class);
    
    private IPromSkuSV promSkuSV=  EcpFrameContextHolder.getBean("promSkuSV", IPromSkuSV.class);
    
    private IPromImportFileSV promImportFileSV=  EcpFrameContextHolder.getBean("promImportFileSV", IPromImportFileSV.class);
    
    public static final int cacheSeconds = 86400;//秒  86400秒=24小时
    
    public ImportSkuTaskSVImpl(PromImportReqDTO promImportReqDTO,PromSku promSku,PromStockLimit promStockLimit,CountDownLatch countDownLatch)

    {
        this.promImportReqDTO=promImportReqDTO;
        this.promStockLimit=promStockLimit;
        this.promSku=promSku;
        this.countDownLatch=countDownLatch;

    }

    public void run()

    {
        try

        {
            //验证是否 单品id+促销编码+有效的已经存在  不存在insert（这里仅仅过滤和既往是否存在相同，当前的重复数据已在上面程序过滤了）
            PromSkuDTO promSkuDTO=new PromSkuDTO();
            promSkuDTO.setPageSize(1);
            promSkuDTO.setPromId(promSku.getPromId());
            promSkuDTO.setSkuId(promSku.getSkuId());
            promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);
            
            PageResponseDTO<PromSkuRespDTO> page= promSkuSV.querySkuPromPage(promSkuDTO);
            
            if(page!=null && page.getCount()>0){
                LogUtil.info(MODULE, "存在相同的单品编码 更新促销数量"+JSON.toJSONString(promSku));
                promSkuDTO.setUpdateStaff(promSku.getCreateStaff());
                promSkuDTO.setPromCnt(promSku.getPromCnt());
                promSkuSV.updatePromSkuCnt(promSkuDTO);
            }else{
            
            promSkuSV.savePromSku(promSku);
            //库存量设置
            promSkuSV.savePromStockLimit(promStockLimit);
            }
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, "ImportSkuTaskSVImpl传入参数="+JSON.toJSONString(promSku));
          LogUtil.error(MODULE, ex.toString());
        }finally{
            if(countDownLatch!=null){
                countDownLatch.countDown();
                //最后一笔 需要insert
                if(countDownLatch.getCount()==0){
                   try{
                        CacheUtil.addItem(promImportReqDTO.getFileId(), "1", cacheSeconds);
                        
                        PromImportFileReqDTO promImportFileReqDTO=new PromImportFileReqDTO();
                        promImportFileReqDTO.setCreateStaff(promImportReqDTO.getCreateStaff());
                        promImportFileReqDTO.setFileId(promImportReqDTO.getFileId());
                        promImportFileReqDTO.setFileName(promImportReqDTO.getFileName());
                        promImportFileReqDTO.setImportDesc(promImportReqDTO.getImportDesc());
                        promImportFileReqDTO.setImportType(promImportReqDTO.getImportType());
                        
                        promImportFileSV.save(promImportFileReqDTO);
                        
                        //solr消息 记录
                        PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promImportReqDTO.getPromId());
                        
                        if(promInfoDTO!=null){
                            Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
                            ObjectCopyUtil.copyObjValue(promInfoDTO, prom2SolrReqDTO, "", false);
                            prom2SolrReqDTO.setPromId(promInfoDTO.getId());
                            prom2SolrSV.save(prom2SolrReqDTO);
                        }
                        
                   }catch(Exception ex){
                       LogUtil.error(MODULE, ex.toString());
                   }
                }
            }
        }
    }
}
