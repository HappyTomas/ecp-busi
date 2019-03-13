package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdBackGift implements Serializable {
    private Long id;

    private Long backId;

    private String orderId;

    private String orderSubId;

    private String procType;

    private Long giftId;

    private Long giftCount;

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

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Long giftCount) {
        this.giftCount = giftCount;
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
        sb.append(", giftId=").append(giftId);
        sb.append(", giftCount=").append(giftCount);
        sb.append("]");
        return sb.toString();
    }
}