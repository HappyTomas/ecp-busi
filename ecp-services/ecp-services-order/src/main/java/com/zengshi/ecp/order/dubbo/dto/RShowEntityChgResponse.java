package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RShowEntityChgResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2128199384298476273L;
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
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;
    /** 
     * gdsId:商品编号. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;
    /** 
     * gdsName:商品名称. 
     * @since JDK 1.6 
     */ 
    private String gdsName;
    /** 
     * skuId:单品编号. 
     * @since JDK 1.6 
     */ 
    private Long skuId;
    /** 
     * skuInfo:单品属性串. 
     * @since JDK 1.6 
     */ 
    private String skuInfo;
    /** 
     * entityCode:实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCode;
    /** 
     * sendDate:发货时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp sendDate;
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
    public String getExpressNo() {
        return expressNo;
    }
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public String getSkuInfo() {
        return skuInfo;
    }
    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo;
    }
    public String getEntityCode() {
        return entityCode;
    }
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
    public Timestamp getSendDate() {
        return sendDate;
    }
    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }
}

