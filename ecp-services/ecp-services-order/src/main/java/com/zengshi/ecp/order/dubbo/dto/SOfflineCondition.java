package com.zengshi.ecp.order.dubbo.dto;

public class SOfflineCondition {
    /** 
     * orderId:订单ID. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * offlineNo:线下支付流水号. 
     * @since JDK 1.6 
     */ 
    private Long offlineNo;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getOfflineNo() {
        return offlineNo;
    }
    public void setOfflineNo(Long offlineNo) {
        this.offlineNo = offlineNo;
    }

}

