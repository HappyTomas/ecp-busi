package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupVarLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupVarLimitConverter extends AbstractConverter<CoupVarLimitReqDTO,CoupVarLimit> {
    @Override
    public void populate(CoupVarLimitReqDTO coupVarLimitReqDTO,CoupVarLimit coupVarLimit) {
        ConversionHelper.copyProperties(coupVarLimitReqDTO, coupVarLimit,null,false);
    }
}
