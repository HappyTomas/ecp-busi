package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupFreeze;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFreezeReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupFreezeConverter extends AbstractConverter<CoupFreezeReqDTO,CoupFreeze> {
    @Override
    public void populate(CoupFreezeReqDTO coupFreezeReqDTO, CoupFreeze coupFreeze) {
        ConversionHelper.copyProperties(coupFreezeReqDTO, coupFreeze,null,false);
    }
}
