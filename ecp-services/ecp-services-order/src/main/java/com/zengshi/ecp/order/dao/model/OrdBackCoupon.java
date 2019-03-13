package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdBackCoupon implements Serializable {
    private Long id;

    private Long backId;

    private String orderId;

    private String orderSubId;

    private String procType;

    private Long couponTypeId;

    private String couponTypeName;

    private Long couponCnt;

    private Long couponAmount;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType == null ? null : procType.trim();
    }

    public Long getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(Long couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName == null ? null : couponTypeName.trim();
    }

    public Long getCouponCnt() {
        return couponCnt;
    }

    public void setCouponCnt(Long couponCnt) {
        this.couponCnt = couponCnt;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", backId=").append(backId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", procType=").append(procType);
        sb.append(", couponTypeId=").append(couponTypeId);
        sb.append(", couponTypeName=").append(couponTypeName);
        sb.append(", couponCnt=").append(couponCnt);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append("]");
        return sb.toString();
    }
}