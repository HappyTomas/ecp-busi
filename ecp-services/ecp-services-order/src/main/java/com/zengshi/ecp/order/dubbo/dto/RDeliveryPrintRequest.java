package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RDeliveryPrintRequest extends BaseInfo {

    /** 
     * serialVersionUID: . 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 9017579632642814378L;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

