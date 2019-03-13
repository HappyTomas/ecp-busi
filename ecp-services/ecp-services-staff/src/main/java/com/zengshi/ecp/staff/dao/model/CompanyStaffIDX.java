package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class CompanyStaffIDX implements Serializable {
    private Long staffId;

    private Long companyId;

    private String applyStatus;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", companyId=").append(companyId);
        sb.append(", applyStatus=").append(applyStatus);
        sb.append("]");
        return sb.toString();
    }
}