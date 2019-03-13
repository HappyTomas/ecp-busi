package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupDetailPresentConverter extends AbstractConverter<CoupDetailReqDTO,CoupPresentReqDTO> {
    @Override
    public void populate(CoupDetailReqDTO coupDetailReqDTO,CoupPresentReqDTO coupPresentReqDTO) {
        ConversionHelper.copyProperties(coupDetailReqDTO, coupPresentReqDTO,null,false);
    }
}
