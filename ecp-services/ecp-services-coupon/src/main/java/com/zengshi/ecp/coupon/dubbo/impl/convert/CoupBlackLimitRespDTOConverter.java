package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupBlackLimit;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupBlackLimitRespDTOConverter extends AbstractConverter<CoupBlackLimit, CoupBlackLimitRespDTO> {
    @Override
    public void populate(CoupBlackLimit coupBlackLimit, CoupBlackLimitRespDTO coupBlackLimitRespDTO) {
        ConversionHelper.copyProperties(coupBlackLimit, coupBlackLimitRespDTO,null,false);
    }
    
}
