package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class ShopNameIDX implements Serializable {
    private String shopName;

    private Long shopId;

    private static final long serialVersionUID = 1L;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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
        sb.append(", shopName=").append(shopName);
        sb.append(", shopId=").append(shopId);
        sb.append("]");
        return sb.toString();
    }
}