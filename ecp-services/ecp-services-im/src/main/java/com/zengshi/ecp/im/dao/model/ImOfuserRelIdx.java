package com.zengshi.ecp.im.dao.model;

import java.io.Serializable;

public class ImOfuserRelIdx implements Serializable {
    private String ofStaffCode;

    private String staffCode;

    private Long staffId;

    private static final long serialVersionUID = 1L;

    public String getOfStaffCode() {
        return ofStaffCode;
    }

    public void setOfStaffCode(String ofStaffCode) {
        this.ofStaffCode = ofStaffCode == null ? null : ofStaffCode.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ofStaffCode=").append(ofStaffCode);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }
}