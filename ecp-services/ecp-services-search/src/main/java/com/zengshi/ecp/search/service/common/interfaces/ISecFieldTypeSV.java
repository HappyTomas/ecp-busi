package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecFieldType;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecFieldTypeSV extends IGeneralSQLSV{
    
    /**
     * 获取所有Solr字段类型
     * @return
     * @throws BusinessException
     */
    public List<SecFieldType> listSecFieldType(SecFieldTypeReqDTO secFieldTypeReqDTO) throws BusinessException;

}

