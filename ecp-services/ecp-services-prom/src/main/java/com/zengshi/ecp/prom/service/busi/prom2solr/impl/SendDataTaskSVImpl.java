package com.zengshi.ecp.prom.service.busi.prom2solr.impl;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-26 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SendDataTaskSVImpl implements Runnable {

    private static final String MODULE = SendDataTaskSVImpl.class.getName();

    private final String taskName; 

    private final String paramName;

    private IPromSolrSV promSolrSV=  EcpFrameContextHolder.getBean("promSolrSV", IPromSolrSV.class);
    
    public SendDataTaskSVImpl(String name, String paramJson)

    {
        taskName = name;
        paramName = paramJson;

    }

    public void run()

    {
        try

        {
            Prom2SolrReqDTO prom2SolrReqDTO =new Prom2SolrReqDTO();
            prom2SolrReqDTO=JSON.parseObject(paramName, Prom2SolrReqDTO.class);
            
            //发送solr关键字
            prom2SolrReqDTO.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_1);
            promSolrSV.sendData(prom2SolrReqDTO);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, ex.toString());
        }
    }
}
