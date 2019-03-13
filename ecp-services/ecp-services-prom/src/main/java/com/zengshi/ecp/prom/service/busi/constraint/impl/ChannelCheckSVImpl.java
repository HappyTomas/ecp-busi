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
public class ChannelCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = ChannelCheckSVImpl.class.getName();

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
        //没有设置 渠道来源规则
        if (StringUtil.isEmpty(promConstaintDTO.getChannel())
                || PromConstants.PromConstraint.CHANNEL_0.equals(promConstaintDTO.getChannel())) {
            // 促销无设置渠道门槛
            return Boolean.FALSE;
        }
        //设置渠道来源规则
        return Boolean.TRUE;
    }

    /**
     * TODO能否参与促销，验证 区域
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO,
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
        
        // 没有业务渠道来源 不能购买
        if (StringUtil.isEmpty(promRuleCheckDTO.getChannelValue())) {
            LogUtil.warn(MODULE, "渠道为空");
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                throw new BusinessException("prom.400105");
            }
            return Boolean.FALSE;
        }
        // 渠道设置一致
        if (promConstaintDTO.getChannelValue().contains(promRuleCheckDTO.getChannelValue())) {
            return Boolean.TRUE;
        }
        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
            throw new BusinessException("prom.400105");
        }
        return Boolean.FALSE;
    }
}
