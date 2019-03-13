package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditABCPayDetail implements Serializable {
    private Long id;

    private Timestamp checkDate;

    private String mercCode;

    private String transType;

    private String orderId;

    private Timestamp transTime;

    private String transAmount;

    private String merchAcctId;

    private String merchAcctMoney;

    private String payeeAcct;

    private String acctType;

    private String fee;

    private String stagingFee;

    private Timestamp accountingDate;

    private String transNo;

    private String transno9014;

    private String oldOrderId;

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

    public String getMercCode() {
        return mercCode;
    }

    public void setMercCode(String mercCode) {
        this.mercCode = mercCode == null ? null : mercCode.trim();
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Timestamp getTransTime() {
        return transTime;
    }

    public void setTransTime(Timestamp transTime) {
        this.transTime = transTime;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount == null ? null : transAmount.trim();
    }

    public String getMerchAcctId() {
        return merchAcctId;
    }

    public void setMerchAcctId(String merchAcctId) {
        this.merchAcctId = merchAcctId == null ? null : merchAcctId.trim();
    }

    public String getMerchAcctMoney() {
        return merchAcctMoney;
    }

    public void setMerchAcctMoney(String merchAcctMoney) {
        this.merchAcctMoney = merchAcctMoney == null ? null : merchAcctMoney.trim();
    }

    public String getPayeeAcct() {
        return payeeAcct;
    }

    public void setPayeeAcct(String payeeAcct) {
        this.payeeAcct = payeeAcct == null ? null : payeeAcct.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee == null ? null : fee.trim();
    }

    public String getStagingFee() {
        return stagingFee;
    }

    public void setStagingFee(String stagingFee) {
        this.stagingFee = stagingFee == null ? null : stagingFee.trim();
    }

    public Timestamp getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Timestamp accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public String getTransno9014() {
        return transno9014;
    }

    public void setTransno9014(String transno9014) {
        this.transno9014 = transno9014 == null ? null : transno9014.trim();
    }

    public String getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(String oldOrderId) {
        this.oldOrderId = oldOrderId == null ? null : oldOrderId.trim();
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
        sb.append(", mercCode=").append(mercCode);
        sb.append(", transType=").append(transType);
        sb.append(", orderId=").append(orderId);
        sb.append(", transTime=").append(transTime);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", merchAcctId=").append(merchAcctId);
        sb.append(", merchAcctMoney=").append(merchAcctMoney);
        sb.append(", payeeAcct=").append(payeeAcct);
        sb.append(", acctType=").append(acctType);
        sb.append(", fee=").append(fee);
        sb.append(", stagingFee=").append(stagingFee);
        sb.append(", accountingDate=").append(accountingDate);
        sb.append(", transNo=").append(transNo);
        sb.append(", transno9014=").append(transno9014);
        sb.append(", oldOrderId=").append(oldOrderId);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}