package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class StockZeroHisKey implements Serializable {
    /**
     * 产品ID
     */
    private Long gdsId;

    /**
     * 单品ID
     */
    private Long skuId;

    private static final long serialVersionUID = 1L;

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
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
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append("]");
        return sb.toString();
    }
}
