package com.zengshi.ecp.prom.service.busi.valid.impl;

import org.drools.core.util.StringUtils;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV;
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
public class PromConstraintValidSVImpl extends GeneralSQLSVImpl implements IPromValidSV {

    private static final String MODULE = PromConstraintValidSVImpl.class.getName();

    private boolean needToVerified;

    public boolean isNeedToVerified() {
        return needToVerified;
    }

    /**
     * xml 配置设置 true false
     * 
     * @param needToVerified
     * @author huangjx
     */
    public void setNeedToVerified(boolean needToVerified) {
        this.needToVerified = needToVerified;
    }

    /**
     * TODO 促销信息录入-促销规则，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV#needToVerified(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        return needToVerified;
    }

    /**
     * TODO 促销规则验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV#valid(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    @Override
    public void valid(PromDTO promDTO) throws BusinessException {
        // 客户等级 用户组 区域等等验证规则
        if (promDTO.getPromConstraintDTO() != null) {
            PromConstraintDTO promConstaintDTO = promDTO.getPromConstraintDTO();
            if (PromConstants.PromConstraint.CUSTGROUP_1.equals(promConstaintDTO.getCustGroup())
                    && StringUtils.isEmpty(promConstaintDTO.getCustGroupValue())) {
                // 请选择用户组
                throw new BusinessException("prom.400035");

            }
            if (PromConstants.PromConstraint.CUSTLEVEL_1.equals(promConstaintDTO.getCustLevel())
                    && StringUtils.isEmpty(promConstaintDTO.getCustLevelValue())) {
                // 请选择客户等级
                throw new BusinessException("prom.400036");
            }
            if (PromConstants.PromConstraint.CHANNEL_1.equals(promConstaintDTO.getChannel())
                    && StringUtils.isEmpty(promConstaintDTO.getChannelValue())) {
                // 请选择渠道来源
                throw new BusinessException("prom.400037");
            }
            if (PromConstants.PromConstraint.AREA_1.equals(promConstaintDTO.getArea())
                    && StringUtils.isEmpty(promConstaintDTO.getAreaValue())) {
                // 请选择区域
                throw new BusinessException("prom.400038");
            }
            // 购买次数限制
            if (PromConstants.PromConstraint.LIMITTIMESTYPE_1.equals(promConstaintDTO.getLimitTimesType())
                    || PromConstants.PromConstraint.LIMITTIMESTYPE_2.equals(promConstaintDTO.getLimitTimesType())
                    || PromConstants.PromConstraint.LIMITTIMESTYPE_3.equals(promConstaintDTO.getLimitTimesType())) {
                if (StringUtils.isEmpty(promConstaintDTO.getLimitTimesTypeValue())) {
                    // 当选择 日 月 年时 需要设置 限制次数
                    throw new BusinessException("prom.400039");
                }
            }
            // 购买总量限制
            if (PromConstants.PromConstraint.LIMITTOTALTYPE_1.equals(promConstaintDTO.getLimitTotalType())
                    || PromConstants.PromConstraint.LIMITTOTALTYPE_2.equals(promConstaintDTO.getLimitTotalType())
                    || PromConstants.PromConstraint.LIMITTOTALTYPE_3.equals(promConstaintDTO.getLimitTotalType())) {
                if (StringUtils.isEmpty(promConstaintDTO.getLimitTotalTypeValue())) {
                    // 当选择 日 月 年时 需要设置 限制购买总量
                    throw new BusinessException("prom.400040");
                }
            }
            // 购买数量限制 无限制
        }
    }

}
