package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AcctInfoTemp implements Serializable {
    private Long id;

    private Long staffId;

    private String acctType;

    private String adaptType;

    private String shopId;

    private String staffCode;

    private String tradeMoney;

    private String isGood;

    private String isCommit;

    private String badDataLoc;

    private String recordDesc;

    private Timestamp createTime;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getAdaptType() {
        return adaptType;
    }

    public void setAdaptType(String adaptType) {
        this.adaptType = adaptType == null ? null : adaptType.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney == null ? null : tradeMoney.trim();
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood == null ? null : isGood.trim();
    }

    public String getIsCommit() {
        return isCommit;
    }

    public void setIsCommit(String isCommit) {
        this.isCommit = isCommit == null ? null : isCommit.trim();
    }

    public String getBadDataLoc() {
        return badDataLoc;
    }

    public void setBadDataLoc(String badDataLoc) {
        this.badDataLoc = badDataLoc == null ? null : badDataLoc.trim();
    }

    public String getRecordDesc() {
        return recordDesc;
    }

    public void setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc == null ? null : recordDesc.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", acctType=").append(acctType);
        sb.append(", adaptType=").append(adaptType);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", tradeMoney=").append(tradeMoney);
        sb.append(", isGood=").append(isGood);
        sb.append(", isCommit=").append(isCommit);
        sb.append(", badDataLoc=").append(badDataLoc);
        sb.append(", recordDesc=").append(recordDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}