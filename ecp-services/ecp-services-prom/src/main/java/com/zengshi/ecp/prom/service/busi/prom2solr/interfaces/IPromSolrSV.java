package com.zengshi.ecp.prom.service.busi.prom2solr.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSolrReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromSolrSV extends IGeneralSQLSV{
     
    /**
     * 发送促销id的信息到solr
     * @param promSolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendSolr(PromSolrReqDTO promSolrReqDTO) throws BusinessException;
    
 
    /**
     * 通过促销id获得列表数据
     * @param promSolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
    
 
    /**
     * 获得促销列表
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendPromGdsData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
    /**
     * 通过促销id获得列表数据
     * @param promSolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendDataThread(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
 
    /**
     * 通过促销id获得列表数据
     * @param strJson
     * @throws BusinessException
     * @author huangjx
     */
    public void sendData(String strJson) throws BusinessException;
}
