package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class CustThirdIdx implements Serializable {
    private Long id;

    private String thirdCode;

    private Long staffId;

    private String thirdCodeType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode == null ? null : thirdCode.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getThirdCodeType() {
        return thirdCodeType;
    }

    public void setThirdCodeType(String thirdCodeType) {
        this.thirdCodeType = thirdCodeType == null ? null : thirdCodeType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", thirdCode=").append(thirdCode);
        sb.append(", staffId=").append(staffId);
        sb.append(", thirdCodeType=").append(thirdCodeType);
        sb.append("]");
        return sb.toString();
    }
}