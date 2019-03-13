package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupFreeze;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFreezeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupFreezeRespDTOConverter extends AbstractConverter<CoupFreeze, CoupFreezeRespDTO> {
    @Override
    public void populate(CoupFreeze coupFreeze, CoupFreezeRespDTO coupFreezeRespDTO) {
        ConversionHelper.copyProperties(coupFreeze, coupFreezeRespDTO,null,false);
    }
    
}
