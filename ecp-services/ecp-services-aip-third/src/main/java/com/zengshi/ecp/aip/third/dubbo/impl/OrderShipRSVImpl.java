package com.zengshi.ecp.aip.third.dubbo.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderShipRSV;
import com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces.IOrderShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class OrderShipRSVImpl implements IOrderShipRSV {

	@Resource
	private IOrderShipSV defaultOrderShipSV;
	
	@Override
	public Map<String,Object> ship(OrderShipReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(req)){
			String value="AIPTHIRD.100017";
			throw new BusinessException(value);
		}
		if(StringUtil.isEmpty(req.getTid())){
			String value="AIPTHIRD.100021";
			throw new BusinessException(value);
		}
		if(StringUtil.isBlank(req.getOutSid())){
			String value="AIPTHIRD.100022";
			throw new BusinessException(value);
		}	
		try{
			return defaultOrderShipSV.ship(req);
		}catch(Exception e){
			String value = "AIPTHIRD.ERRORS.100001";
			throw new BusinessException(value);
		}
		
	}

}
