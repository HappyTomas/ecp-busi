package com.zengshi.ecp.search.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecZkMapper;
import com.zengshi.ecp.search.dao.model.SecZk;
import com.zengshi.ecp.search.dao.model.SecZkCriteria;
import com.zengshi.ecp.search.dao.model.SecZkCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecZkSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:02:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecZkSVImpl extends GeneralSQLSVImpl implements ISecZkSV {
    
    @Autowired
    private SecZkMapper secZkMapper;

    @Override
    public List<SecZk> listSecZk() throws BusinessException {
        SecZkCriteria criteria = new SecZkCriteria();
        Criteria cr = criteria.createCriteria();

        // 过滤无效数据
        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);

        List<SecZk> secZkList = this.secZkMapper.selectByExample(criteria);
        return secZkList;
    }

}

