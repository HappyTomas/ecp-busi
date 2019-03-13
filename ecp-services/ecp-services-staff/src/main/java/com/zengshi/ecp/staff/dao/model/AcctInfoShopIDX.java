package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AcctInfoShopIDX implements Serializable {
    private Long id;

    private Long shopId;

    private Long staffId;

    private String acctType;

    private String status;

    private String adaptType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAdaptType() {
        return adaptType;
    }

    public void setAdaptType(String adaptType) {
        this.adaptType = adaptType == null ? null : adaptType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffId=").append(staffId);
        sb.append(", acctType=").append(acctType);
        sb.append(", status=").append(status);
        sb.append(", adaptType=").append(adaptType);
        sb.append("]");
        return sb.toString();
    }
}