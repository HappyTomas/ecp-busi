package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeIdx;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupConsumeIdxConverter extends AbstractConverter<CoupConsume,CoupConsumeIdx> {
    @Override
    public void populate(CoupConsume coupConsume,CoupConsumeIdx CoupConsumeIdx) {
        ConversionHelper.copyProperties(coupConsume, CoupConsumeIdx,null,false);
    }
}
