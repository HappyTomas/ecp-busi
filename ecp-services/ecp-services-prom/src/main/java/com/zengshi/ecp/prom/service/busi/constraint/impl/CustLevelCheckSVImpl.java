package com.zengshi.ecp.prom.service.busi.constraint.impl;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CustLevelCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = CustLevelCheckSVImpl.class.getName();

    /**
     * TODO能否参与促销，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO)
     * @param promConstaintDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromConstraintDTO promConstaintDTO) throws BusinessException {
        //没有设置 等级规则
       if (StringUtil.isEmpty(promConstaintDTO.getCustLevel())
                || PromConstants.PromConstraint.CUSTLEVEL_0.equals(promConstaintDTO.getCustLevel())) {
            // 促销无设置用户等级门槛
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * TODO 能否参与促销，验证 区域
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promConstaintDTO
     *            促销配置限制参数
     * @param promRuleCheckDTO
     *            促销业务参数
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        // 没有配置 不能购买
        if (StringUtil.isEmpty(promRuleCheckDTO.getCustLevelValue())) {
            LogUtil.warn(MODULE, "用户级别为空");
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                throw new BusinessException("prom.400107");
            }
            return Boolean.FALSE;
        }
        // 如果用户级别和设置级别一致 return true;
        if (promConstaintDTO.getCustLevelValue()
                .contains(promRuleCheckDTO.getCustLevelValue())) {
            return Boolean.TRUE;
        }
        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
            throw new BusinessException("prom.400107");
        }
        return Boolean.FALSE;
    }
}
