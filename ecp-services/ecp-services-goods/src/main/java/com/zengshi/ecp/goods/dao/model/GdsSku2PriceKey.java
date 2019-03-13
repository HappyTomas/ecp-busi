package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class GdsSku2PriceKey implements Serializable {
    private Long priceId;

    private Long skuId;

    private static final long serialVersionUID = 1L;

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", priceId=").append(priceId);
        sb.append(", skuId=").append(skuId);
        sb.append("]");
        return sb.toString();
    }
}
