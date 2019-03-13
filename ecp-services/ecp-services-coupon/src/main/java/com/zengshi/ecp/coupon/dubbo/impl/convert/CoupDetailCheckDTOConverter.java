package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;

/**
 */
public class CoupDetailCheckDTOConverter extends AbstractConverter<CoupDetail,CoupDetailRespDTO> {
    @Override
    public void populate(CoupDetail coupDetail,CoupDetailRespDTO coupDetailRespDTO) {
        ConversionHelper.copyProperties(coupDetail,coupDetailRespDTO, null,false);
    }
}
