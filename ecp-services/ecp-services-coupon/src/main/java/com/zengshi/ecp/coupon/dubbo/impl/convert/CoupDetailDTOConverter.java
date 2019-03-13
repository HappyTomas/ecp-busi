package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupDetailDTOConverter extends AbstractConverter<CoupDetail,CoupDetailReqDTO> {
    @Override
    public void populate(CoupDetail coupDetail,CoupDetailReqDTO coupDetailReqDTO) {
        ConversionHelper.copyProperties(coupDetail,coupDetailReqDTO, null,false);
    }
}
