package com.zengshi.ecp.prom.service.busi.importdata.impl;

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
public class ImportDataDeleteTaskSVImpl implements Runnable {

    private static final String MODULE = ImportDataDeleteTaskSVImpl.class.getName();

    private final PromImportReqDTO promImportReqDTO;

    private IPromImportSV promImportSV=  EcpFrameContextHolder.getBean("promImportSV", IPromImportSV.class);
    
    public ImportDataDeleteTaskSVImpl(PromImportReqDTO promImportReqDTO)

    {
        this.promImportReqDTO=promImportReqDTO;

    }

    public void run()

    {
        try

        {
            promImportSV.deleteById(promImportReqDTO);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, "ImportDataTaskSVImpl传入参数="+JSON.toJSONString(promImportReqDTO));
          LogUtil.error(MODULE, ex.toString());
        }
    }
}
