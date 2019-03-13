package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecFieldProcessorMapper;
import com.zengshi.ecp.search.dao.model.SecFieldProcessor;
import com.zengshi.ecp.search.dao.model.SecFieldProcessorCriteria;
import com.zengshi.ecp.search.dao.model.SecFieldProcessorCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldProcessorSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecFieldProcessorSVImpl extends GeneralSQLSVImpl implements ISecFieldProcessorSV {

    @Autowired
    private SecFieldProcessorMapper secFieldProcessorMapper;

    @Override
    public String saveSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO)
            throws BusinessException {
        
        // DTO转BO,同名属性自动赋值
        SecFieldProcessor secFieldProcessor = new SecFieldProcessor();
        BeanTransfermationUtils.dto2bo(secFieldProcessor, secFieldProcessorReqDTO);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secFieldProcessor.setCreateStaff(secFieldProcessorReqDTO.getStaff().getId());
        secFieldProcessor.setCreateTime(t);
        secFieldProcessor.setUpdateStaff(secFieldProcessorReqDTO.getStaff().getId());
        secFieldProcessor.setUpdateTime(t);

        this.secFieldProcessorMapper.insert(secFieldProcessor);

        return secFieldProcessor.getProcessorName();
    }

    @Override
    public List<SecFieldProcessor> listSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO) throws BusinessException {
        SecFieldProcessorCriteria criteria = new SecFieldProcessorCriteria();
        criteria.setOrderByClause("processor_name asc");
        
        Criteria cr = criteria.createCriteria();

        if(StringUtils.equals(secFieldProcessorReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecFieldProcessor> secFieldProcessorList = this.secFieldProcessorMapper.selectByExample(criteria);
        return secFieldProcessorList;
    }

}
