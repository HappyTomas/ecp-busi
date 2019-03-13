package com.zengshi.ecp.general.prom.interfaces;

import java.util.List;
import com.zengshi.ecp.general.prom.dto.GdsPromDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-03-15 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public interface IPromMsg2SolrRSV {
    /**
     * 根据gdsId skuId shopId 获得参与促销列表，并发送消息到solr
     * @param queryPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendMsg2SolrByNewSku(GdsPromDTO gdsPromDTO) throws BusinessException;
    /**
     * 根据gdsId skuId shopId列表 获得对应参与促销列表，并发送消息到solr
     * @param queryPromDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void sendMsg2SolrByNewSkuList(List<GdsPromDTO> gdsPromDTOList) throws BusinessException;
}
