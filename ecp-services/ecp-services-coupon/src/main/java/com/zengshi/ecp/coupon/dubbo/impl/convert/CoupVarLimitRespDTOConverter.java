package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupVarLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupVarLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupVarLimitRespDTOConverter extends AbstractConverter<CoupVarLimit, CoupVarLimitRespDTO> {
    @Override
    public void populate(CoupVarLimit coupVarLimit, CoupVarLimitRespDTO coupVarLimitRespDTO) {
        ConversionHelper.copyProperties(coupVarLimit, coupVarLimitRespDTO,null,false);
    }
    
}
