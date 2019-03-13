package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustSensitiveLog implements Serializable {
    private Long staffId;

    private String actionType;

    private String sensitiveType;

    private String sensitiveDesc;

    private Timestamp createTime;

    private String createStaffCode;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getSensitiveType() {
        return sensitiveType;
    }

    public void setSensitiveType(String sensitiveType) {
        this.sensitiveType = sensitiveType == null ? null : sensitiveType.trim();
    }

    public String getSensitiveDesc() {
        return sensitiveDesc;
    }

    public void setSensitiveDesc(String sensitiveDesc) {
        this.sensitiveDesc = sensitiveDesc == null ? null : sensitiveDesc.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaffCode() {
        return createStaffCode;
    }

    public void setCreateStaffCode(String createStaffCode) {
        this.createStaffCode = createStaffCode == null ? null : createStaffCode.trim();
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
        sb.append(", staffId=").append(staffId);
        sb.append(", actionType=").append(actionType);
        sb.append(", sensitiveType=").append(sensitiveType);
        sb.append(", sensitiveDesc=").append(sensitiveDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaffCode=").append(createStaffCode);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}