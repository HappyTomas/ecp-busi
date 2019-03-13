package com.zengshi.ecp.order.dubbo.dto;

public class SEntityAddImportInfo {
    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;
    /** 
     * entityCode:实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCode;
    public String getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
    public String getEntityCode() {
        return entityCode;
    }
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
}

