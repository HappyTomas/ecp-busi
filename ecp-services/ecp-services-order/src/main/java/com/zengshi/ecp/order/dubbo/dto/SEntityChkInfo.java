package com.zengshi.ecp.order.dubbo.dto;

public class SEntityChkInfo {
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * importNo:批次号. 
     * @since JDK 1.6 
     */ 
    private Long importNo;
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
     * entityCode:实体编号. 或实体编号变更中的变更后字段
     * @since JDK 1.6 
     */ 
    private String entityCode;
    
    /** 
     * entityCodeBf:用于实体编号变更中的变更前字段. 
     * @since JDK 1.6 
     */ 
    private String entityCodeBf;
    public String getEntityCodeBf() {
        return entityCodeBf;
    }
    public void setEntityCodeBf(String entityCodeBf) {
        this.entityCodeBf = entityCodeBf;
    }
    /** 
     * remark:备注. 
     * @since JDK 1.6 
     */ 
    private String remark;
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getImportNo() {
        return importNo;
    }
    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }
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
    public String getEntityCode() {
        return entityCode;
    }
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

