package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupType;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupTypeConverter extends AbstractConverter<CoupTypeReqDTO, CoupType> {
    @Override
    public void populate(CoupTypeReqDTO coupTypeReqDTO, CoupType coupType) {
        ConversionHelper.copyProperties(coupTypeReqDTO, coupType,null,false);
    }
}
