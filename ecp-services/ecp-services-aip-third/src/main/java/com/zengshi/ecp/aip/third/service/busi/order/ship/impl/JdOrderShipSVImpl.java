package com.zengshi.ecp.aip.third.service.busi.order.ship.impl;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces.IOrderShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdOrderShipSVImpl implements IOrderShipSV {

	@Override
	public Map<String,Object> ship(OrderShipReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", false);
		map.put("msg", "京东接口还未开通哦");
		return map;
	}

}
