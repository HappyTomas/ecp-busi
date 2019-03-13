package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdBackApply implements Serializable {
    private Long id;

    private String orderId;

    private String applyType;

    private Timestamp applyTime;

    private String status;

    private String backType;

    private String backTypeName;

    private String backDesc;

    private String isEntire;

    private Long num;

    private Long staffId;

    private Long shopId;

    private String checkDesc;

    private String payType;

    private Long backMoney;

    private Long backScore;

    private Long backAccount;

    private Long expressId;

    private String express;

    private String expressNo;

    private Long siteId;

    private String isInAuditFile;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String isCompenstate;

    private String payTranNo;

    private Timestamp refundTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType == null ? null : backType.trim();
    }

    public String getBackTypeName() {
        return backTypeName;
    }

    public void setBackTypeName(String backTypeName) {
        this.backTypeName = backTypeName == null ? null : backTypeName.trim();
    }

    public String getBackDesc() {
        return backDesc;
    }

    public void setBackDesc(String backDesc) {
        this.backDesc = backDesc == null ? null : backDesc.trim();
    }

    public String getIsEntire() {
        return isEntire;
    }

    public void setIsEntire(String isEntire) {
        this.isEntire = isEntire == null ? null : isEntire.trim();
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc == null ? null : checkDesc.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Long getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Long backMoney) {
        this.backMoney = backMoney;
    }

    public Long getBackScore() {
        return backScore;
    }

    public void setBackScore(Long backScore) {
        this.backScore = backScore;
    }

    public Long getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(Long backAccount) {
        this.backAccount = backAccount;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getIsInAuditFile() {
        return isInAuditFile;
    }

    public void setIsInAuditFile(String isInAuditFile) {
        this.isInAuditFile = isInAuditFile == null ? null : isInAuditFile.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getIsCompenstate() {
        return isCompenstate;
    }

    public void setIsCompenstate(String isCompenstate) {
        this.isCompenstate = isCompenstate == null ? null : isCompenstate.trim();
    }

    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo == null ? null : payTranNo.trim();
    }

    public Timestamp getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", applyType=").append(applyType);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", status=").append(status);
        sb.append(", backType=").append(backType);
        sb.append(", backTypeName=").append(backTypeName);
        sb.append(", backDesc=").append(backDesc);
        sb.append(", isEntire=").append(isEntire);
        sb.append(", num=").append(num);
        sb.append(", staffId=").append(staffId);
        sb.append(", shopId=").append(shopId);
        sb.append(", checkDesc=").append(checkDesc);
        sb.append(", payType=").append(payType);
        sb.append(", backMoney=").append(backMoney);
        sb.append(", backScore=").append(backScore);
        sb.append(", backAccount=").append(backAccount);
        sb.append(", expressId=").append(expressId);
        sb.append(", express=").append(express);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", siteId=").append(siteId);
        sb.append(", isInAuditFile=").append(isInAuditFile);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", isCompenstate=").append(isCompenstate);
        sb.append(", payTranNo=").append(payTranNo);
        sb.append(", refundTime=").append(refundTime);
        sb.append("]");
        return sb.toString();
    }
}