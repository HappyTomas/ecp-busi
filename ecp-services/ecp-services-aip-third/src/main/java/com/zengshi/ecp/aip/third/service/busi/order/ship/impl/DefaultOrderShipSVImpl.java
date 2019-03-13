package com.zengshi.ecp.aip.third.service.busi.order.ship.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces.IOrderShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultOrderShipSVImpl implements IOrderShipSV {

	public static final String MODULE = DefaultOrderShipSVImpl.class.getName();

	private HashMap<String,IOrderShipSV> orderShipSVMap;

	@Override
	public Map<String,Object> ship(OrderShipReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub
		return this.getOrderShipSV(req.findBaseShopAuth()).ship(req);
	}

	public HashMap<String,IOrderShipSV> getOrderShipSVMap() {
		return orderShipSVMap;
	}

	public void setOrderShipSVMap(HashMap<String,IOrderShipSV> orderShipSVMap) {
		this.orderShipSVMap = orderShipSVMap;
	}

	private IOrderShipSV getOrderShipSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IOrderShipSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(orderShipSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!orderShipSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=orderShipSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}
}
