package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class StockRepInfoIdx implements Serializable {
    private Long repCode;

    private Long typeId;

    private String catgCode;

    private Long gdsId;

    private Long skuId;

    private Long stockId;

    private String status;

    private static final long serialVersionUID = 1L;

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

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

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", repCode=").append(repCode);
        sb.append(", typeId=").append(typeId);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", stockId=").append(stockId);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
