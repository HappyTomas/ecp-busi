package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PayShopCfgLog implements Serializable {
    private Long id;

    private Long shopId;

    private String payWay;

    private String payWayName;

    private String mercCode;

    private String branchId;

    private String cisNo;

    private String shopAccount;

    private String payeeAccount;

    private String payeeName;

    private String posId;

    private String cerName;

    private String keyName;

    private String cerPassword;

    private String useFlag;

    private Short showOrder;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private Timestamp logTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getMercCode() {
        return mercCode;
    }

    public void setMercCode(String mercCode) {
        this.mercCode = mercCode == null ? null : mercCode.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getCisNo() {
        return cisNo;
    }

    public void setCisNo(String cisNo) {
        this.cisNo = cisNo == null ? null : cisNo.trim();
    }

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount == null ? null : shopAccount.trim();
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId == null ? null : posId.trim();
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName == null ? null : cerName.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getCerPassword() {
        return cerPassword;
    }

    public void setCerPassword(String cerPassword) {
        this.cerPassword = cerPassword == null ? null : cerPassword.trim();
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }

    public Short getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Short showOrder) {
        this.showOrder = showOrder;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", payWay=").append(payWay);
        sb.append(", payWayName=").append(payWayName);
        sb.append(", mercCode=").append(mercCode);
        sb.append(", branchId=").append(branchId);
        sb.append(", cisNo=").append(cisNo);
        sb.append(", shopAccount=").append(shopAccount);
        sb.append(", payeeAccount=").append(payeeAccount);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", posId=").append(posId);
        sb.append(", cerName=").append(cerName);
        sb.append(", keyName=").append(keyName);
        sb.append(", cerPassword=").append(cerPassword);
        sb.append(", useFlag=").append(useFlag);
        sb.append(", showOrder=").append(showOrder);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", logTime=").append(logTime);
        sb.append("]");
        return sb.toString();
    }
}