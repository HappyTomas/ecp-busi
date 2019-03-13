package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupInfoLog;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class CoupInfoLogConverter extends AbstractConverter<CoupInfoReqDTO,CoupInfoLog> {
    @Override
    public void populate(CoupInfoReqDTO coupInfoReqDTO,CoupInfoLog coupInfoLong) {
        ConversionHelper.copyProperties(coupInfoReqDTO, coupInfoLong,null,false);
        if(StringUtil.isNotEmpty(coupInfoReqDTO.getCoupValue())){
        	coupInfoLong.setCoupValue(coupInfoReqDTO.getCoupValue());
        }
        
    }
}
