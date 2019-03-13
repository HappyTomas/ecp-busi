package com.zengshi.ecp.search.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecArgsMapper;
import com.zengshi.ecp.search.dao.model.SecArgs;
import com.zengshi.ecp.search.dao.model.SecArgsCriteria;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecArgsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午7:42:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecArgsSVImpl extends GeneralSQLSVImpl implements ISecArgsSV {
    
    @Autowired
    private SecArgsMapper secArgsMapper;

    @Override
    public List<SecArgs> listSecArgs() throws BusinessException {
        SecArgsCriteria criteria = new SecArgsCriteria();
        com.zengshi.ecp.search.dao.model.SecArgsCriteria.Criteria cr = criteria.createCriteria(); 

        // 过滤无效数据
        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);

        List<SecArgs> secArgsList = this.secArgsMapper.selectByExample(criteria);
        return secArgsList;
    }

}

