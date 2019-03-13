package com.zengshi.ecp.aip.third.service.busi.order.detail.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces.IOrderDetailSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultOrderDetailSVImpl implements IOrderDetailSV{
    
    public static final String MODULE = DefaultOrderDetailSVImpl.class.getName();

    private HashMap<String,IOrderDetailSV> orderDetailSVMap;
    
	public HashMap<String,IOrderDetailSV> getOrderDetailSVMap() {
		return orderDetailSVMap;
	}

	public void setOrderDetailSVMap(HashMap<String,IOrderDetailSV> orderDetailSVMap) {
		this.orderDetailSVMap = orderDetailSVMap;
	}

	 /**
     * 
     * querySimpleOrderDetail:订单简单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    @Override
    public OrderRespDTO querySimpleOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException{
    	orderReqDTO.checkOrderParams();
    	return this.getOrderDetailSV(orderReqDTO.findBaseShopAuth()).querySimpleOrderDetail(orderReqDTO);
    }

    /**
     * 
     * queryOrderDetail:订单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    @Override
    public OrderRespDTO queryOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException{
    	orderReqDTO.checkOrderParams();
    	return this.getOrderDetailSV(orderReqDTO.findBaseShopAuth()).queryOrderDetail(orderReqDTO);
    }
    
	
	private IOrderDetailSV getOrderDetailSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IOrderDetailSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(orderDetailSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!orderDetailSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=orderDetailSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}
}

