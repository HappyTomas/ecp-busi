package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupGetLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupGetLimitConverter extends AbstractConverter<CoupGetLimitReqDTO,CoupGetLimit> {
    @Override
    public void populate(CoupGetLimitReqDTO coupGetLimitReqDTO,CoupGetLimit coupGetLimit) {
        ConversionHelper.copyProperties(coupGetLimitReqDTO, coupGetLimit,null,false);
    }
}
