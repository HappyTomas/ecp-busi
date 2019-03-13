package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupCatgLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupCatgLimitConverter extends AbstractConverter<CoupCatgLimitReqDTO,CoupCatgLimit> {
    @Override
    public void populate(CoupCatgLimitReqDTO coupCatgLimitReqDTO,CoupCatgLimit coupCatgLimit) {
        ConversionHelper.copyProperties(coupCatgLimitReqDTO, coupCatgLimit,null,false);
    }
}
