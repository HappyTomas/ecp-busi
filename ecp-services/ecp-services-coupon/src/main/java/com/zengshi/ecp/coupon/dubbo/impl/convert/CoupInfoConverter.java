package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class CoupInfoConverter extends AbstractConverter<CoupInfoReqDTO,CoupInfo> {
    @Override
    public void populate(CoupInfoReqDTO coupInfoReqDTO,CoupInfo coupInfo) {
        ConversionHelper.copyProperties(coupInfoReqDTO, coupInfo,null,false);
        
        if(StringUtil.isNotEmpty(coupInfoReqDTO.getCoupValue())){
        	coupInfo.setCoupValue(coupInfoReqDTO.getCoupValue());
        }
    }
}
