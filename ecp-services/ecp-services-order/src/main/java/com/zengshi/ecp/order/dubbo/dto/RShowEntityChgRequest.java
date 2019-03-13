package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RShowEntityChgRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5707502816396319017L;
    /** 
     * begDate:查询开始时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp begDate;
    
    /** 
     * endDate:查询结束时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp endDate;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * remarks:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * entityCode:实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCode;
    /** 
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Timestamp getBegDate() {
        return begDate;
    }

    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    
    

}

