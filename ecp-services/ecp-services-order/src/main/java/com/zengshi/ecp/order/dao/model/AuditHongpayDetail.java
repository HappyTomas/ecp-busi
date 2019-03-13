package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditHongpayDetail implements Serializable {
    private Long id;

    private Timestamp checkDate;

    private Timestamp transDate;

    private String payType;

    private String mercCode;

    private String orderId;

    private String transNo;

    private String bankCardId;

    private String bankCardName;

    private String payeerAccountId;

    private String transType;

    private String transAmount;

    private String payResult;

    private Timestamp transTime;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getMercCode() {
        return mercCode;
    }

    public void setMercCode(String mercCode) {
        this.mercCode = mercCode == null ? null : mercCode.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId == null ? null : bankCardId.trim();
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName == null ? null : bankCardName.trim();
    }

    public String getPayeerAccountId() {
        return payeerAccountId;
    }

    public void setPayeerAccountId(String payeerAccountId) {
        this.payeerAccountId = payeerAccountId == null ? null : payeerAccountId.trim();
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount == null ? null : transAmount.trim();
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult == null ? null : payResult.trim();
    }

    public Timestamp getTransTime() {
        return transTime;
    }

    public void setTransTime(Timestamp transTime) {
        this.transTime = transTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", transDate=").append(transDate);
        sb.append(", payType=").append(payType);
        sb.append(", mercCode=").append(mercCode);
        sb.append(", orderId=").append(orderId);
        sb.append(", transNo=").append(transNo);
        sb.append(", bankCardId=").append(bankCardId);
        sb.append(", bankCardName=").append(bankCardName);
        sb.append(", payeerAccountId=").append(payeerAccountId);
        sb.append(", transType=").append(transType);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", payResult=").append(payResult);
        sb.append(", transTime=").append(transTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}