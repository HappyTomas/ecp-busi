package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLog;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupDetailLogConverter extends AbstractConverter<CoupDetail,CoupDetailLog> {
    @Override
    public void populate(CoupDetail coupDetail,CoupDetailLog coupDetailLog) {
        ConversionHelper.copyProperties(coupDetail, coupDetailLog,null,false);
    }
}
