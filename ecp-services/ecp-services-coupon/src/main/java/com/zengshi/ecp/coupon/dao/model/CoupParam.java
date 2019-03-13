package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupParam implements Serializable {
    private String ruleCode;

    private String ruleName;

    private String ruleType;

    private String serviceId;

    private String defSwitch;

    private String ifShow;

    private Integer sortNo;

    private String status;

    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getDefSwitch() {
        return defSwitch;
    }

    public void setDefSwitch(String defSwitch) {
        this.defSwitch = defSwitch == null ? null : defSwitch.trim();
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleCode=").append(ruleCode);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", defSwitch=").append(defSwitch);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}
