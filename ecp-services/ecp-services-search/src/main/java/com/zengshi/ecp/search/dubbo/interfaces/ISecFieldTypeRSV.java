package com.zengshi.ecp.search.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:31:39  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecFieldTypeRSV {
    
    /**
     * 获取所有Solr字段类型
     * @return
     * @throws BusinessException
     */
    public List<SecFieldTypeRespDTO> listSecFieldType(SecFieldTypeReqDTO secFieldTypeReqDTO) throws BusinessException;

}

