package com.zengshi.ecp.aip.third.service.busi.order.detail.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces.IOrderDetailSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class YouzanOrderDetailSVImpl implements IOrderDetailSV{
    
    public static final String MODULE = YouzanOrderDetailSVImpl.class.getName();

    
   
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
    	return null;
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
    	return null;
    }
}

