package com.zengshi.ecp.order.service.busi.impl.pay;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWay;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道创建工厂<br>
 * Date:2015年10月2日上午11:33:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayWayFactory {
    public static final String MODULE = PayWayFactory.class.getName();
    
    public static IPayWay getPayWay(PayWayEnum payWay) {
	   if(null == payWay){
		   throw new IllegalArgumentException("支付通道不能传空！");
	   }
	   IPayWay aPayWay = null;
	   try{
	       aPayWay = EcpFrameContextHolder.getBean(payWay.getBeanName(), IPayWay.class);
	   }catch(Exception e){
		   throw new RuntimeException("调用参数异常",e);
	   }
	   return aPayWay;
    }
}