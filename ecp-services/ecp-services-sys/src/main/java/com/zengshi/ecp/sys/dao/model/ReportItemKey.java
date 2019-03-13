package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReportItemKey implements Serializable {
    private String itemCode;

    private Long shopId;

    private String itemSource;

    private Timestamp calDate;

    private static final long serialVersionUID = 1L;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getItemSource() {
        return itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource == null ? null : itemSource.trim();
    }

    public Timestamp getCalDate() {
        return calDate;
    }

    public void setCalDate(Timestamp calDate) {
        this.calDate = calDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemCode=").append(itemCode);
        sb.append(", shopId=").append(shopId);
        sb.append(", itemSource=").append(itemSource);
        sb.append(", calDate=").append(calDate);
        sb.append("]");
        return sb.toString();
    }
}
