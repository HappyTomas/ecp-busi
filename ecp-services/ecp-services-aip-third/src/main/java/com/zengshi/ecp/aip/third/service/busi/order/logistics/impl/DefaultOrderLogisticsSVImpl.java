package com.zengshi.ecp.aip.third.service.busi.order.logistics.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderLogisticsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.order.logistics.interfaces.IOrderLogisticsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public class DefaultOrderLogisticsSVImpl implements IOrderLogisticsSV {
	
	public static final String MODULE = DefaultOrderLogisticsSVImpl.class.getName();
	private HashMap<String,IOrderLogisticsSV> orderLogisticsSVMap;
	
	private IOrderLogisticsSV getOrderLogisticsSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IOrderLogisticsSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(orderLogisticsSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!orderLogisticsSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=orderLogisticsSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}
	
	public HashMap<String,IOrderLogisticsSV> getOrderLogisticsSVMap() {
		return orderLogisticsSVMap;
	}
	public void setOrderLogisticsSVMap(HashMap<String,IOrderLogisticsSV> orderLogisticsSVMap) {
		this.orderLogisticsSVMap = orderLogisticsSVMap;
	}

	@Override
	public OrderLogisticsRespDTO queryLogisticsCompany(OrderLogisticsReqDTO orderLogisticsReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return this.getOrderLogisticsSV(orderLogisticsReqDTO.findBaseShopAuth()).queryLogisticsCompany(orderLogisticsReqDTO);
	}
	    
}
