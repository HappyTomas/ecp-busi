package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV;
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
public class ReadExcelDataTaskSVImpl implements Callable<PromImportReqDTO> {

    private static final String MODULE = ReadExcelDataTaskSVImpl.class.getName();

    private final List<Object> excelDataList;
    
    private final String fileId;
    
    private final Long shopId;
    
    private final Long siteId;
    
    private final PromImportReqDTO promImportReqDTO;
    
    private final CountDownLatch countDownLatch ;
    
    private IPromImportSV promImportSV=  EcpFrameContextHolder.getBean("promImportSV", IPromImportSV.class);
    
    public ReadExcelDataTaskSVImpl(List excelDataList,CountDownLatch countDownLatch,String fileId, Long shopId, Long siteId, PromImportReqDTO promImportReqDTO)

    {
        this.excelDataList=excelDataList;
        this.countDownLatch=countDownLatch;
        this.fileId=fileId;
        this.shopId=shopId;
        this.siteId=siteId;
        this.promImportReqDTO=promImportReqDTO;

    }

    public PromImportReqDTO call() throws Exception  
    {
        try
        {
            return promImportSV.readExcel(excelDataList, fileId, shopId, siteId, promImportReqDTO);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, "ReadExcelDataTaskSVImpl传入参数="+JSON.toJSONString(promImportReqDTO));
          LogUtil.error(MODULE, ex.toString());
        }
        return null;
    }
}
