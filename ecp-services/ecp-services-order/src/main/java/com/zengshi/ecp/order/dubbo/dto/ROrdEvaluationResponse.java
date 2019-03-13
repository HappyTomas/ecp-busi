package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdEvaluationResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2318209433503103442L;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * gdsId:商品编码. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;
    /** 
     * skuId:单品编码. 
     * @since JDK 1.6 
     */ 
    private Long skuId;
    /** 
     * orderId:主订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * orderTime:下单单. 
     * @since JDK 1.6 
     */ 
    private Timestamp orderTime;
    /** 
     * subId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String subId;
    public Timestamp getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSubId() {
        return subId;
    }
    public void setSubId(String subId) {
        this.subId = subId;
    }

}

