package com.zengshi.ecp.search.service.common.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecFieldTypeMapper;
import com.zengshi.ecp.search.dao.model.SecFieldType;
import com.zengshi.ecp.search.dao.model.SecFieldTypeCriteria;
import com.zengshi.ecp.search.dao.model.SecFieldTypeCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecFieldTypeSVImpl extends GeneralSQLSVImpl implements ISecFieldTypeSV {
    
    @Autowired
    private SecFieldTypeMapper secFieldTypeMapper;

    @Override
    public List<SecFieldType> listSecFieldType(SecFieldTypeReqDTO secFieldTypeReqDTO) throws BusinessException {
        SecFieldTypeCriteria criteria = new SecFieldTypeCriteria();
        criteria.setOrderByClause("type_name asc");
        
        Criteria cr = criteria.createCriteria();

        if(StringUtils.equals(secFieldTypeReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecFieldType> secFieldTypeList = this.secFieldTypeMapper.selectByExample(criteria);
        return secFieldTypeList;
    }

}

