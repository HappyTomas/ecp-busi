package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PayIntfNotifyLog implements Serializable {
    private Long id;

    private String payWay;

    private String typeCode;

    private String orderId;

    private Long staffId;

    private String paywayRequestNo;

    private Timestamp requestTime;

    private Timestamp responseTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getPaywayRequestNo() {
        return paywayRequestNo;
    }

    public void setPaywayRequestNo(String paywayRequestNo) {
        this.paywayRequestNo = paywayRequestNo == null ? null : paywayRequestNo.trim();
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payWay=").append(payWay);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", orderId=").append(orderId);
        sb.append(", staffId=").append(staffId);
        sb.append(", paywayRequestNo=").append(paywayRequestNo);
        sb.append(", requestTime=").append(requestTime);
        sb.append(", responseTime=").append(responseTime);
        sb.append("]");
        return sb.toString();
    }
}