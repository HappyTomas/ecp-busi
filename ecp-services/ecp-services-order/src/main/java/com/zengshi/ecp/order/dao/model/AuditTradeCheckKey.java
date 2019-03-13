package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class AuditTradeCheckKey implements Serializable {
    private String payWay;

    private String transNo;

    private static final long serialVersionUID = 1L;

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payWay=").append(payWay);
        sb.append(", transNo=").append(transNo);
        sb.append("]");
        return sb.toString();
    }
}