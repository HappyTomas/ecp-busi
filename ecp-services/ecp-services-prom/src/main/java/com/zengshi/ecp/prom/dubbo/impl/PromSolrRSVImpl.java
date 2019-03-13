package com.zengshi.ecp.prom.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-23 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSolrRSVImpl implements IPromSolrRSV {
    
    @Resource
    private  IPromSolrSV promSolrSV;
    
    /**
     * TODO发送消息
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV#sendData(com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO)
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException{
        
        if(prom2SolrReqDTO!=null && PromConstants.Prom2Solr.SOLR_TYPE_2.equals(prom2SolrReqDTO.getSolrType())){
            promSolrSV.sendPromGdsData(prom2SolrReqDTO);
        }else{
          //promSolrSV.sendData(prom2SolrReqDTO);
          promSolrSV.sendDataThread(prom2SolrReqDTO);
        }
    }
     
}
