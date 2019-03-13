package com.zengshi.ecp.coupon.dubbo.impl.convert;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-31下午2:43:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupMyDTOConverter extends AbstractConverter<CoupMeReqDTO, CoupDetailReqDTO> {
	
	@Override
    public void populate( CoupMeReqDTO coupMeReqDTO,CoupDetailReqDTO coupDetailReqDTO) {
        
        ConversionHelper.copyProperties(coupMeReqDTO, coupDetailReqDTO,null,false);
        
        
    }
    
}
