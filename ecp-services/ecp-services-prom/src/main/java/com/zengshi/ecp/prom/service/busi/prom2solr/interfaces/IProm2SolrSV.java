package com.zengshi.ecp.prom.service.busi.prom2solr.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
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
public interface IProm2SolrSV extends IGeneralSQLSV{
  
    /**
     * 消息中间表保存
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
    
    /**
     * 更新
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public long update(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
 
    /**
     * 促销消息 数据  不分页
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<Prom2SolrRespDTO> queryProm2SolrList(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException;
 
    /**
     * 列表 分页功能
     * @param prom2SolrReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<Prom2SolrRespDTO> queryProm2SolrForPage(Prom2SolrReqDTO prom2SolrReqDTO)
            throws BusinessException;
}
