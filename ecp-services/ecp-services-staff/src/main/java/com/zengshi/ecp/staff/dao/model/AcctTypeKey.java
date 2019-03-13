package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AcctTypeKey implements Serializable {
    private String acctType;

    private String adaptType;

    private Long shopId;

    private static final long serialVersionUID = 1L;

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getAdaptType() {
        return adaptType;
    }

    public void setAdaptType(String adaptType) {
        this.adaptType = adaptType == null ? null : adaptType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", acctType=").append(acctType);
        sb.append(", adaptType=").append(adaptType);
        sb.append(", shopId=").append(shopId);
        sb.append("]");
        return sb.toString();
    }
}