package com.zengshi.ecp.prom.dubbo.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromSolrRSV {

    
    /**
     * 通过促销id获得列表数据
     * @param promSolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
    
}
