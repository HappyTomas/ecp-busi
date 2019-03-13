package com.zengshi.ecp.search.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecSolrServerMapper;
import com.zengshi.ecp.search.dao.model.SecSolrServer;
import com.zengshi.ecp.search.dao.model.SecSolrServerCriteria;
import com.zengshi.ecp.search.dao.model.SecSolrServerCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecSolrServerSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:02:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecSolrServerSVImpl extends GeneralSQLSVImpl implements ISecSolrServerSV {
    
    @Autowired
    private SecSolrServerMapper secSolrServerMapper;

    @Override
    public List<SecSolrServer> listSecSolrServer() throws BusinessException {
        SecSolrServerCriteria criteria = new SecSolrServerCriteria();
        Criteria cr = criteria.createCriteria();

        // 过滤无效数据
        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);

        List<SecSolrServer> secSolrServerList = this.secSolrServerMapper.selectByExample(criteria);
        return secSolrServerList;
    }

}

