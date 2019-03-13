package com.zengshi.ecp.prom.service.busi.valid.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
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
public class PromDiscountValidSVImpl extends GeneralSQLSVImpl implements IPromValidSV {

    private static final String MODULE = PromDiscountValidSVImpl.class.getName();

    @Resource
    private IPromTypeSV promTypeSV;

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
     * TODO 促销信息录入-优惠规则，是否需要验证
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
     * TODO 促销优惠规则验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.valid.interfaces.IPromValidSV#valid(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    @Override
    public void valid(PromDTO promDTO) throws BusinessException {
        //促销类型代码 获得促销类型信息
        PromTypeResponseDTO promTypeResponseDTO = promTypeSV.queryPromTypeByCode(promDTO
                .getPromTypeCode());
        //后台没有配置 抛出错误
        if (promTypeResponseDTO == null) {
            throw new BusinessException("prom.400041");
        }
        //服务ID
        String serviceId = promTypeResponseDTO.getServiceId();
        //获得ClassPathXmlApplicationContext context bean
        IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(serviceId, IPromDiscountRuleSV.class);
        if (iPromDiscountRuleService.needToVerified(promDTO)) {
            iPromDiscountRuleService.valid(promDTO.getDiscountRule(),promDTO);
        }
    }

}
