package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class CompanyNameIDX implements Serializable {
    private Long companyId;

    private String companyName;

    private Long staffId;

    private String serialNumbr;

    private static final long serialVersionUID = 1L;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getSerialNumbr() {
        return serialNumbr;
    }

    public void setSerialNumbr(String serialNumbr) {
        this.serialNumbr = serialNumbr == null ? null : serialNumbr.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", staffId=").append(staffId);
        sb.append(", serialNumbr=").append(serialNumbr);
        sb.append("]");
        return sb.toString();
    }
}