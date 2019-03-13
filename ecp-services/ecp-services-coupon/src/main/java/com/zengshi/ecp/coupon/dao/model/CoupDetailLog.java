package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupDetailLog implements Serializable {
    private Long id;

    private Long siteId;

    private Long coupDetailId;

    private String coupNo;

    private Long staffId;

    private Long coupId;

    private Long coupTypeId;

    private Long coupValue;

    private String typeLimit;

    private String useRuleCode;

    private String gainRuleCode;

    private String coupSource;

    private Timestamp useTime;

    private Long shopId;

    private Long batchId;

    private String remark;

    private String ifUse;

    private String status;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getCoupDetailId() {
        return coupDetailId;
    }

    public void setCoupDetailId(Long coupDetailId) {
        this.coupDetailId = coupDetailId;
    }

    public String getCoupNo() {
        return coupNo;
    }

    public void setCoupNo(String coupNo) {
        this.coupNo = coupNo == null ? null : coupNo.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
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

    public String getCoupSource() {
        return coupSource;
    }

    public void setCoupSource(String coupSource) {
        this.coupSource = coupSource == null ? null : coupSource.trim();
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIfUse() {
        return ifUse;
    }

    public void setIfUse(String ifUse) {
        this.ifUse = ifUse == null ? null : ifUse.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", siteId=").append(siteId);
        sb.append(", coupDetailId=").append(coupDetailId);
        sb.append(", coupNo=").append(coupNo);
        sb.append(", staffId=").append(staffId);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupTypeId=").append(coupTypeId);
        sb.append(", coupValue=").append(coupValue);
        sb.append(", typeLimit=").append(typeLimit);
        sb.append(", useRuleCode=").append(useRuleCode);
        sb.append(", gainRuleCode=").append(gainRuleCode);
        sb.append(", coupSource=").append(coupSource);
        sb.append(", useTime=").append(useTime);
        sb.append(", shopId=").append(shopId);
        sb.append(", batchId=").append(batchId);
        sb.append(", remark=").append(remark);
        sb.append(", ifUse=").append(ifUse);
        sb.append(", status=").append(status);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", inactiveTime=").append(inactiveTime);
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
