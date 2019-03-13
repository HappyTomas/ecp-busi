package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupParam;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupParamReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupParamConverter extends AbstractConverter<CoupParamReqDTO, CoupParam> {
    @Override
    public void populate(CoupParamReqDTO coupParamReqDTO, CoupParam coupParam) {
        ConversionHelper.copyProperties(coupParamReqDTO, coupParam,null,false);
    }
}
