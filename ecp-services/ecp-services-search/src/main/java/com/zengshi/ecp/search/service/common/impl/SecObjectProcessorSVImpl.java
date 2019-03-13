package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecObjectProcessorMapper;
import com.zengshi.ecp.search.dao.model.SecObjectProcessor;
import com.zengshi.ecp.search.dao.model.SecObjectProcessorCriteria;
import com.zengshi.ecp.search.dao.model.SecObjectProcessorCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectProcessorSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecObjectProcessorSVImpl extends GeneralSQLSVImpl implements ISecObjectProcessorSV {
    
    @Autowired
    private SecObjectProcessorMapper secObjectProcessorMapper;

    @Override
    public String saveSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO)
            throws BusinessException {
    
        // DTO转BO,同名属性自动赋值
        SecObjectProcessor secObjectProcessor = new SecObjectProcessor();
        BeanTransfermationUtils.dto2bo(secObjectProcessor, secObjectProcessorReqDTO);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secObjectProcessor.setCreateStaff(secObjectProcessorReqDTO.getStaff().getId());
        secObjectProcessor.setCreateTime(t);
        secObjectProcessor.setUpdateStaff(secObjectProcessorReqDTO.getStaff().getId());
        secObjectProcessor.setUpdateTime(t);

        this.secObjectProcessorMapper.insert(secObjectProcessor);

        return secObjectProcessor.getProcessorName();
    }

    @Override
    public List<SecObjectProcessor> listSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO) throws BusinessException {
        SecObjectProcessorCriteria criteria = new SecObjectProcessorCriteria();
        criteria.setOrderByClause("processor_name asc");
        
        Criteria cr = criteria.createCriteria();

        if(StringUtils.equals(secObjectProcessorReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecObjectProcessor> secObjectProcessorList = this.secObjectProcessorMapper.selectByExample(criteria);
        return secObjectProcessorList;
    }

}

