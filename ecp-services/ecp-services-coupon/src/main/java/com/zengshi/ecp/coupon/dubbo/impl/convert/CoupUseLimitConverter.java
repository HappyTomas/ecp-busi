package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupUseLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupUseLimitConverter extends AbstractConverter<CoupUseLimitReqDTO,CoupUseLimit> {
    @Override
    public void populate(CoupUseLimitReqDTO coupUseLimitReqDTO,CoupUseLimit coupUseLimit) {
        ConversionHelper.copyProperties(coupUseLimitReqDTO, coupUseLimit,null,false);
    }
}
