package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupConsumeConverter extends AbstractConverter<CoupConsumeReqDTO,CoupConsume> {
    @Override
    public void populate(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsume coupConsume) {
        ConversionHelper.copyProperties(coupConsumeReqDTO, coupConsume,null,false);
    }
}
