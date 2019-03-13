package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupPresent;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupPresentConverter extends AbstractConverter<CoupPresentReqDTO,CoupPresent> {
    @Override
    public void populate(CoupPresentReqDTO coupPresentReqDTO,CoupPresent coupPresent) {
        ConversionHelper.copyProperties(coupPresentReqDTO, coupPresent,null,false);
    }
}
