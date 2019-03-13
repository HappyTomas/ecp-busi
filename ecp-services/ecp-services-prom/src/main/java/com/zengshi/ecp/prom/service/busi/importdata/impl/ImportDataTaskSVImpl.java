package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.concurrent.CountDownLatch;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-05 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class ImportDataTaskSVImpl implements Runnable {

    private static final String MODULE = ImportDataTaskSVImpl.class.getName();

    private final PromImportReqDTO promImportReqDTO;
    
    private final CountDownLatch countDownLatch ;
    
    private IPromImportSV promImportSV=  EcpFrameContextHolder.getBean("promImportSV", IPromImportSV.class);
    
    private IPromImportFileSV promImportFileSV=  EcpFrameContextHolder.getBean("promImportFileSV", IPromImportFileSV.class);
    
    public static final int cacheSeconds = 86400;//秒  86400秒=24小时
    
    public ImportDataTaskSVImpl(PromImportReqDTO promImportReqDTO,CountDownLatch countDownLatch)

    {
        this.promImportReqDTO=promImportReqDTO;
        this.countDownLatch=countDownLatch;

    }

    public void run()

    {
        try

        {
            promImportSV.save(promImportReqDTO);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, "ImportDataTaskSVImpl传入参数="+JSON.toJSONString(promImportReqDTO));
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
                        
                   }catch(Exception ex){
                       LogUtil.error(MODULE, ex.toString());
                   }
                }
            }
        }
    }
}
