package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditTradeCheck extends AuditTradeCheckKey implements Serializable {
    private Timestamp checkDate;

    private Long transAmount;

    private String orderId;

    private Long staffId;

    private String cardNo;

    private String cardName;

    private Long shopId;

    private Timestamp payTime;

    private String payDescription;

    private String checkStatus;

    private Long orderAmount;

    private String bankName;

    private String payWayName;

    private String auditStatus;

    private Long siteId;

    private String auditType;

    private Long refundOrderAmount;

    private Long refundTransAmount;

    private Long backId;

    private static final long serialVersionUID = 1L;

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription == null ? null : payDescription.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType == null ? null : auditType.trim();
    }

    public Long getRefundOrderAmount() {
        return refundOrderAmount;
    }

    public void setRefundOrderAmount(Long refundOrderAmount) {
        this.refundOrderAmount = refundOrderAmount;
    }

    public Long getRefundTransAmount() {
        return refundTransAmount;
    }

    public void setRefundTransAmount(Long refundTransAmount) {
        this.refundTransAmount = refundTransAmount;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkDate=").append(checkDate);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", orderId=").append(orderId);
        sb.append(", staffId=").append(staffId);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", cardName=").append(cardName);
        sb.append(", shopId=").append(shopId);
        sb.append(", payTime=").append(payTime);
        sb.append(", payDescription=").append(payDescription);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", bankName=").append(bankName);
        sb.append(", payWayName=").append(payWayName);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", siteId=").append(siteId);
        sb.append(", auditType=").append(auditType);
        sb.append(", refundOrderAmount=").append(refundOrderAmount);
        sb.append(", refundTransAmount=").append(refundTransAmount);
        sb.append(", backId=").append(backId);
        sb.append("]");
        return sb.toString();
    }
}