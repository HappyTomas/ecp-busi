package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdEntityImportGroup implements Serializable {

    private static final long serialVersionUID = 2893333584835162751L;
    
    private String orderId;

    private String orderSubId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }

}

