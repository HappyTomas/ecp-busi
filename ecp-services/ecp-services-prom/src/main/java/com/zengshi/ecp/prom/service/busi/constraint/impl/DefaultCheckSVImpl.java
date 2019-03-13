package com.zengshi.ecp.prom.service.busi.constraint.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class DefaultCheckSVImpl extends GeneralSQLSVImpl implements IPromConstraintCheckSV {

    private static final String MODULE = DefaultCheckSVImpl.class.getName();
    
    @Resource
    private IPromQuerySV promQuerySV;

    /**
     * TODO能否参与促销，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO)
     * @param promConstaintDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromConstraintDTO promConstaintDTO) throws BusinessException {
        return Boolean.TRUE;
    }

    /**
     * TODO获得促销设置条件
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV#getPromConstaintInfo(java.lang.String)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromConstraintDTO getPromConstaintInfo(Long promId) throws BusinessException {
        
        return promQuerySV.queryPromConstraint(promId);
    }

    /**
     * TODO能否参与促销，验证 区域
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV#check(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promConstaintDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        return Boolean.FALSE;
    }

}
