package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthStaffEIDX implements Serializable {
    private String email;

    private String staffCode;

    private Long staffId;

    private static final long serialVersionUID = 1L;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        sb.append(", email=").append(email);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }
}