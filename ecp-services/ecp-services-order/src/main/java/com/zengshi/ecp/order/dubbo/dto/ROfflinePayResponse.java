package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROfflinePayResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 824746612549803086L;

    /** 
     * orderType:订单种类. 
     * @since JDK 1.6 
     */ 
    private String orderType;
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * shopName:卖家名称. 
     * @since JDK 1.6 
     */ 
    private String shopName;
    /** 
     * orderTime:下单时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp orderTime;
    /** 
     * orderAmount:总数量. 
     * @since JDK 1.6 
     */ 
    private Long orderAmount;
    /** 
     * realMoney:总金额. 
     * @since JDK 1.6 
     */ 
    private Long realMoney;
    /** 
     * realExpressFee:运费. 
     * @since JDK 1.6 
     */ 
    private Long realExpressFee;
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public Timestamp getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
    public Long getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public Long getRealMoney() {
        return realMoney;
    }
    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }
    public Long getRealExpressFee() {
        return realExpressFee;
    }
    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }
}

