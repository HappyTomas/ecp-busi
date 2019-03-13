package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupUseLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupUseLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupUseLimitRespDTOConverter extends AbstractConverter<CoupUseLimit, CoupUseLimitRespDTO> {
    @Override
    public void populate(CoupUseLimit coupUseLimit, CoupUseLimitRespDTO coupUseLimitRespDTO) {
        ConversionHelper.copyProperties(coupUseLimit, coupUseLimitRespDTO,null,false);
    }
    
}
