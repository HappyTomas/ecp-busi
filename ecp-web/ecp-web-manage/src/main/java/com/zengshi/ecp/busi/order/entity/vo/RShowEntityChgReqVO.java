package com.zengshi.ecp.busi.order.entity.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RShowEntityChgReqVO extends EcpBasePageReqVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4270397471020552750L;
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
    private Timestamp begDate;
    private Timestamp endDate;
    private Long shopId;
    private String orderId;
    private String entityCode;
    private String expressNo;
}

