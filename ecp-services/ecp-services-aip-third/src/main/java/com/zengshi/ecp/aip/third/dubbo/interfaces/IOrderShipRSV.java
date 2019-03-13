package com.zengshi.ecp.aip.third.dubbo.interfaces;

import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrderShipRSV {
	  public Map<String,Object> ship(OrderShipReqDTO req) throws BusinessException;
}
