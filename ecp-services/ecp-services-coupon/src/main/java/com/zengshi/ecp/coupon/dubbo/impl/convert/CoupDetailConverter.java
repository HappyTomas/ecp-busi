package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupDetailConverter extends AbstractConverter<CoupDetailReqDTO,CoupDetail> {
    @Override
    public void populate(CoupDetailReqDTO coupDetailReqDTO,CoupDetail coupDetail) {
        ConversionHelper.copyProperties(coupDetailReqDTO, coupDetail,null,false);
    }
}
