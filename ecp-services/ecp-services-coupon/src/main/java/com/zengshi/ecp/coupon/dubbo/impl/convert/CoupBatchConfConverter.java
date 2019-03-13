package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchConfReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class CoupBatchConfConverter extends AbstractConverter<CoupBatchConfReqDTO,CoupBatchConf> {
    @Override
    public void populate(CoupBatchConfReqDTO coupBatchConfReqDTO, CoupBatchConf coupBatchConf) {
        ConversionHelper.copyProperties(coupBatchConfReqDTO, coupBatchConf,null,false);
    }
}
