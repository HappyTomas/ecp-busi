package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupShopLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupShopLimitRespDTOConverter extends AbstractConverter<CoupShopLimit, CoupShopLimitRespDTO> {
    @Override
    public void populate(CoupShopLimit coupShopLimit, CoupShopLimitRespDTO coupShopLimitRespDTO) {
        ConversionHelper.copyProperties(coupShopLimit, coupShopLimitRespDTO,null,false);
    }
    
}
