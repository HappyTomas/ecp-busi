package com.zengshi.ecp.order.dubbo.dto;

public class SReceiptInfo {
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
     * skuId:单品编号. 
     * @since JDK 1.6 
     */ 
    private Long skuId;
    /** 
     * orderAmount:订购数量. 
     * @since JDK 1.6 
     */ 
    private Long orderAmount;
    /** 
     * stockId:所属库量ID. 
     * @since JDK 1.6 
     */ 
    private Long stockId;
    /** 
     * categoryId:产品分类. 
     * @since JDK 1.6 
     */ 
    private String categoryCode;
    /** 
     * gdsId:商品编号. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;
    
    /** 
     * gdsType:商品类型. 
     * @since JDK 1.6 
     */ 
    private Long gdsType;
    
    
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public Long getGdsType() {
        return gdsType;
    }
    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
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
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    
}

