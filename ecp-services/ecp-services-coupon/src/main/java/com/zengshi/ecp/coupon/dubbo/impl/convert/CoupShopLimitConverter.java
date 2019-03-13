package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupShopLimit;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupShopLimitConverter extends AbstractConverter<CoupShopLimitReqDTO,CoupShopLimit> {
    @Override
    public void populate(CoupShopLimitReqDTO coupShopLimitReqDTO,CoupShopLimit coupShopLimit) {
        ConversionHelper.copyProperties(coupShopLimitReqDTO, coupShopLimit,null,false);
    }
}
