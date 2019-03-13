package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StockOptRecord implements Serializable {
    private Long id;

    private Long stockId;

    private Long optRepCode;

    private Long skuId;

    private String optType;

    private Long count;

    private Long createStaff;

    private Timestamp createTime;

    private String ordOptNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getOptRepCode() {
        return optRepCode;
    }

    public void setOptRepCode(Long optRepCode) {
        this.optRepCode = optRepCode;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOrdOptNo() {
        return ordOptNo;
    }

    public void setOrdOptNo(String ordOptNo) {
        this.ordOptNo = ordOptNo == null ? null : ordOptNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stockId=").append(stockId);
        sb.append(", optRepCode=").append(optRepCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", optType=").append(optType);
        sb.append(", count=").append(count);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", ordOptNo=").append(ordOptNo);
        sb.append("]");
        return sb.toString();
    }
}
