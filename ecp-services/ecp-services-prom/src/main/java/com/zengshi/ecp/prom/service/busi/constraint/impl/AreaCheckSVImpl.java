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
public class AreaCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = AreaCheckSVImpl.class.getName();

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
        
        if(StringUtil.isEmpty(promConstaintDTO.getArea())){
            return Boolean.FALSE;
        }
        if (!PromConstants.PromConstraint.AREA_1.equals(promConstaintDTO.getArea())) {
            // 促销无设置区域门槛 
            return Boolean.FALSE;
        }
        // 返回 true 需要验证
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
        
        //进入该方法 表示相关值 必需符合要求 否则不进入该方法
        
        //如果业务区域 为空 那么 不满足要求 不可用购买
        
        if(StringUtil.isEmpty(promRuleCheckDTO.getAreaValue())){
            //为空 表示限制规则有区域限制 但是当前参数没有区域 不能购买
            LogUtil.warn(MODULE, "区域为空");
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                throw new BusinessException("prom.400103");
            }
            return Boolean.FALSE;
        }
        //如果设置区域为 排除 区域  那么 不存在表示满足要求
        if(PromConstants.PromSys.AREA_EXCLUDE_1.equals(promConstaintDTO.getAreaExclude())){
            // 区域设置一致 不可以购买
            if (promConstaintDTO.getAreaValue().contains(promRuleCheckDTO.getAreaValue())) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }else{
            // 区域设置一致 可以购买
            if (promConstaintDTO.getAreaValue().contains(promRuleCheckDTO.getAreaValue())) {
                return Boolean.TRUE;
            }
        }
        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
            throw new BusinessException("prom.400103");
        }
        return Boolean.FALSE;
    }
}
