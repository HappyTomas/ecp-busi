package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailIdx;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupDetailIdxConverter extends AbstractConverter<CoupDetail,CoupDetailIdx> {
    @Override
    public void populate(CoupDetail coupDetail,CoupDetailIdx coupDetailIdx) {
        ConversionHelper.copyProperties(coupDetail, coupDetailIdx,null,false);
    }
}
