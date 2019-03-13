package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupParam;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class CoupParamRespDTOConverter extends AbstractConverter<CoupParam, CoupParamRespDTO> {
    @Override
    public void populate(CoupParam coupParam, CoupParamRespDTO coupParamRespDTO) {
        
        ConversionHelper.copyProperties(coupParam, coupParamRespDTO,null,false);
        
        if(StringUtil.isEmpty(coupParamRespDTO.getRuleTypeName())){
            coupParamRespDTO.setRuleTypeName(BaseParamUtil.fetchParamValue(CouponConstants.CoupKey.COUP_PARAM_RULE_TYPE, coupParamRespDTO.getRuleType()));
        }
        
    }
}
