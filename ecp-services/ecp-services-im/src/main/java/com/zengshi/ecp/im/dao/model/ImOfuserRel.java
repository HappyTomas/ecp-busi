package com.zengshi.ecp.im.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImOfuserRel implements Serializable {
    private String staffCode;

    private String ofStaffCode;

    private Long createStaff;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getOfStaffCode() {
        return ofStaffCode;
    }

    public void setOfStaffCode(String ofStaffCode) {
        this.ofStaffCode = ofStaffCode == null ? null : ofStaffCode.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffCode=").append(staffCode);
        sb.append(", ofStaffCode=").append(ofStaffCode);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}