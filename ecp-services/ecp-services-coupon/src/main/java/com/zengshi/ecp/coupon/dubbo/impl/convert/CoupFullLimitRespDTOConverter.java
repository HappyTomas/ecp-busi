package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupFullLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupFullLimitRespDTOConverter extends AbstractConverter<CoupFullLimit, CoupFullLimitRespDTO> {
    @Override
    public void populate(CoupFullLimit coupFullLimit, CoupFullLimitRespDTO coupFullLimitRespDTO) {
        ConversionHelper.copyProperties(coupFullLimit, coupFullLimitRespDTO,null,false);
    }
    
}
