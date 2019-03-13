package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupBlackLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupBlackLimitConverter extends AbstractConverter<CoupBlackLimitReqDTO,CoupBlackLimit> {
    @Override
    public void populate(CoupBlackLimitReqDTO coupBlackLimitReqDTO,CoupBlackLimit coupBlackLimit) {
        ConversionHelper.copyProperties(coupBlackLimitReqDTO, coupBlackLimit,null,false);
    }
}
