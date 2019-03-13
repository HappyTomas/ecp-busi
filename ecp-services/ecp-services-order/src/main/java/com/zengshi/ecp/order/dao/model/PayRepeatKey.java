package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class PayRepeatKey implements Serializable {
    private String payWay;

    private String payTransNo;

    private static final long serialVersionUID = 1L;

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPayTransNo() {
        return payTransNo;
    }

    public void setPayTransNo(String payTransNo) {
        this.payTransNo = payTransNo == null ? null : payTransNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payWay=").append(payWay);
        sb.append(", payTransNo=").append(payTransNo);
        sb.append("]");
        return sb.toString();
    }
}