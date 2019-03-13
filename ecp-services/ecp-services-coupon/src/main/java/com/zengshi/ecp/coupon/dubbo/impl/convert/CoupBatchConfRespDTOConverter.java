package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBatchConfRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupBatchConfRespDTOConverter extends AbstractConverter<CoupBatchConf,CoupBatchConfRespDTO> {
    @Override
    public void populate(CoupBatchConf coupBatchConf,CoupBatchConfRespDTO coupBatchConfRespDTO) {
        ConversionHelper.copyProperties(coupBatchConf,coupBatchConfRespDTO,null,false);
    }
}
