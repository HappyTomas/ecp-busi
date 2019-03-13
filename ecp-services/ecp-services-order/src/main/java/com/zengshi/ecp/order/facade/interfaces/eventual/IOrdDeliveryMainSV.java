package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;

public interface IOrdDeliveryMainSV{
    /** 
     * orderDelivery:订单确认发货. <br/> 
     * @author cbl 
     * @param rConfirmDeliveRequest 
     * @since JDK 1.6 
     */ 
    public void orderDelivery(RConfirmDeliveRequest rConfirmDeliveRequest);
}

