package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupFullLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupFullLimitConverter extends AbstractConverter<CoupFullLimitReqDTO,CoupFullLimit> {
    @Override
    public void populate(CoupFullLimitReqDTO coupFullLimitReqDTO,CoupFullLimit coupFullLimit) {
        ConversionHelper.copyProperties(coupFullLimitReqDTO, coupFullLimit,null,false);
    }
}
