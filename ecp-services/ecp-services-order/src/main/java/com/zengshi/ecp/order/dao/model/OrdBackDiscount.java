package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdBackDiscount implements Serializable {
    private Long id;

    private Long backId;

    private String orderId;

    private String orderSubId;

    private String procType;

    private String discountType;

    private String acctType;

    private Long amount;

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

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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
        sb.append(", discountType=").append(discountType);
        sb.append(", acctType=").append(acctType);
        sb.append(", amount=").append(amount);
        sb.append("]");
        return sb.toString();
    }
}