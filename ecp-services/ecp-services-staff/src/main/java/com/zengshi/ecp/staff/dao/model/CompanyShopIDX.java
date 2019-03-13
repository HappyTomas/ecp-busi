package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class CompanyShopIDX implements Serializable {
    private Long shopId;

    private Long companyId;

    private String applyStatus;

    private static final long serialVersionUID = 1L;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", companyId=").append(companyId);
        sb.append(", applyStatus=").append(applyStatus);
        sb.append("]");
        return sb.toString();
    }
}