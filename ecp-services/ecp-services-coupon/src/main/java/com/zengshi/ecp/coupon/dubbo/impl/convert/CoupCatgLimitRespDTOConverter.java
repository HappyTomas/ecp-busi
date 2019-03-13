package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupCatgLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupCatgLimitRespDTOConverter extends AbstractConverter<CoupCatgLimit, CoupCatgLimitRespDTO> {
    @Override
    public void populate(CoupCatgLimit coupCatgLimit, CoupCatgLimitRespDTO coupCatgLimitRespDTO) {
        ConversionHelper.copyProperties(coupCatgLimit, coupCatgLimitRespDTO,null,false);
    }
    
}
