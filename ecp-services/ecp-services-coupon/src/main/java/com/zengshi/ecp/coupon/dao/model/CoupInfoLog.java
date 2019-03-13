package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupInfoLog implements Serializable {
    private Long id;

    private Long coupId;

    private String coupCode;

    private Long coupTypeId;

    private Long siteId;

    private String coupName;

    private Long coupValue;

    private String typeLimit;

    private String useRuleCode;

    private String gainRuleCode;

    private Long shopId;

    private String ifCode;

    private String ifBack;

    private Long coupNum;

    private Long getNum;

    private String effType;

    private Integer fixedTime;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private String status;

    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Timestamp createTimeLog;

    private Long createStaffLog;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public String getCoupCode() {
        return coupCode;
    }

    public void setCoupCode(String coupCode) {
        this.coupCode = coupCode == null ? null : coupCode.trim();
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName == null ? null : coupName.trim();
    }

    public Long getCoupValue() {
        return coupValue;
    }

    public void setCoupValue(Long coupValue) {
        this.coupValue = coupValue;
    }

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit == null ? null : typeLimit.trim();
    }

    public String getUseRuleCode() {
        return useRuleCode;
    }

    public void setUseRuleCode(String useRuleCode) {
        this.useRuleCode = useRuleCode == null ? null : useRuleCode.trim();
    }

    public String getGainRuleCode() {
        return gainRuleCode;
    }

    public void setGainRuleCode(String gainRuleCode) {
        this.gainRuleCode = gainRuleCode == null ? null : gainRuleCode.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIfCode() {
        return ifCode;
    }

    public void setIfCode(String ifCode) {
        this.ifCode = ifCode == null ? null : ifCode.trim();
    }

    public String getIfBack() {
        return ifBack;
    }

    public void setIfBack(String ifBack) {
        this.ifBack = ifBack == null ? null : ifBack.trim();
    }

    public Long getCoupNum() {
        return coupNum;
    }

    public void setCoupNum(Long coupNum) {
        this.coupNum = coupNum;
    }

    public Long getGetNum() {
        return getNum;
    }

    public void setGetNum(Long getNum) {
        this.getNum = getNum;
    }

    public String getEffType() {
        return effType;
    }

    public void setEffType(String effType) {
        this.effType = effType == null ? null : effType.trim();
    }

    public Integer getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(Integer fixedTime) {
        this.fixedTime = fixedTime;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Timestamp getCreateTimeLog() {
        return createTimeLog;
    }

    public void setCreateTimeLog(Timestamp createTimeLog) {
        this.createTimeLog = createTimeLog;
    }

    public Long getCreateStaffLog() {
        return createStaffLog;
    }

    public void setCreateStaffLog(Long createStaffLog) {
        this.createStaffLog = createStaffLog;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupCode=").append(coupCode);
        sb.append(", coupTypeId=").append(coupTypeId);
        sb.append(", siteId=").append(siteId);
        sb.append(", coupName=").append(coupName);
        sb.append(", coupValue=").append(coupValue);
        sb.append(", typeLimit=").append(typeLimit);
        sb.append(", useRuleCode=").append(useRuleCode);
        sb.append(", gainRuleCode=").append(gainRuleCode);
        sb.append(", shopId=").append(shopId);
        sb.append(", ifCode=").append(ifCode);
        sb.append(", ifBack=").append(ifBack);
        sb.append(", coupNum=").append(coupNum);
        sb.append(", getNum=").append(getNum);
        sb.append(", effType=").append(effType);
        sb.append(", fixedTime=").append(fixedTime);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", inactiveTime=").append(inactiveTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", createTimeLog=").append(createTimeLog);
        sb.append(", createStaffLog=").append(createStaffLog);
        sb.append("]");
        return sb.toString();
    }
}
