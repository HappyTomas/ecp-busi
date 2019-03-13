package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupGetLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */																			
public class CoupGetLimitRespDTOConverter extends AbstractConverter<CoupGetLimit, CoupGetLimitRespDTO> {
    @Override
    public void populate(CoupGetLimit coupGetLimit, CoupGetLimitRespDTO coupGetLimitRespDTO) {
        ConversionHelper.copyProperties(coupGetLimit, coupGetLimitRespDTO,null,false);
    }
    
}
