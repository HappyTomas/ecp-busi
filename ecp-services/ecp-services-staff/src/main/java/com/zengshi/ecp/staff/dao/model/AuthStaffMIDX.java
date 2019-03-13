package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthStaffMIDX implements Serializable {
    private String serialNumber;

    private String staffCode;

    private Long staffId;

    private static final long serialVersionUID = 1L;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
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
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }
}