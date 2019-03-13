package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupTypeLog implements Serializable {
    private Long id;

    private Long typeId;

    private String coupTypeName;

    private String useRuleCode;

    private String getRuleCode;

    private String typeLimit;

    private String status;

    private Integer sortNo;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getCoupTypeName() {
        return coupTypeName;
    }

    public void setCoupTypeName(String coupTypeName) {
        this.coupTypeName = coupTypeName == null ? null : coupTypeName.trim();
    }

    public String getUseRuleCode() {
        return useRuleCode;
    }

    public void setUseRuleCode(String useRuleCode) {
        this.useRuleCode = useRuleCode == null ? null : useRuleCode.trim();
    }

    public String getGetRuleCode() {
        return getRuleCode;
    }

    public void setGetRuleCode(String getRuleCode) {
        this.getRuleCode = getRuleCode == null ? null : getRuleCode.trim();
    }

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit == null ? null : typeLimit.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", coupTypeName=").append(coupTypeName);
        sb.append(", useRuleCode=").append(useRuleCode);
        sb.append(", getRuleCode=").append(getRuleCode);
        sb.append(", typeLimit=").append(typeLimit);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
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
