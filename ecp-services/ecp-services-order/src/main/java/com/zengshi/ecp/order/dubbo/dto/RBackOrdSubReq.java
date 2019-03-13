package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBackOrdSubReq extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6638551009416317065L;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;

    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;
    
    /** 
     * num:数量. 
     * @since JDK 1.6 
     */ 
    private Long num;

    
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

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
    
}

