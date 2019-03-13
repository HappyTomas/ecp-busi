package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class GdsCatlog2ShopKey implements Serializable {
    private Long catlogId;

    private Long shopId;

    private static final long serialVersionUID = 1L;

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
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
        sb.append(", catlogId=").append(catlogId);
        sb.append(", shopId=").append(shopId);
        sb.append("]");
        return sb.toString();
    }
}
