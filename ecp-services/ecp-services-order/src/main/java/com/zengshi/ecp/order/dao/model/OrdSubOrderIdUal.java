package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdSubOrderIdUal implements Serializable {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8214126330532784832L;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

