package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class GdsExcelImp implements Serializable {
    private Long importId;

    private String busiSrc;

    private Long gdsId;

    private String catgCode;

    private String gdsName;

    private Long shopId;

    private Long gdsType;

    private String gdsTitle;

    private String gdsPrice;

    private String gdsPropStr;

    private Long gdsStock;

    private String failReason;

    private static final long serialVersionUID = 1L;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public String getBusiSrc() {
        return busiSrc;
    }

    public void setBusiSrc(String busiSrc) {
        this.busiSrc = busiSrc == null ? null : busiSrc.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getGdsType() {
        return gdsType;
    }

    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
    }

    public String getGdsTitle() {
        return gdsTitle;
    }

    public void setGdsTitle(String gdsTitle) {
        this.gdsTitle = gdsTitle == null ? null : gdsTitle.trim();
    }

    public String getGdsPrice() {
        return gdsPrice;
    }

    public void setGdsPrice(String gdsPrice) {
        this.gdsPrice = gdsPrice == null ? null : gdsPrice.trim();
    }

    public String getGdsPropStr() {
        return gdsPropStr;
    }

    public void setGdsPropStr(String gdsPropStr) {
        this.gdsPropStr = gdsPropStr == null ? null : gdsPropStr.trim();
    }

    public Long getGdsStock() {
        return gdsStock;
    }

    public void setGdsStock(Long gdsStock) {
        this.gdsStock = gdsStock;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", importId=").append(importId);
        sb.append(", busiSrc=").append(busiSrc);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", shopId=").append(shopId);
        sb.append(", gdsType=").append(gdsType);
        sb.append(", gdsTitle=").append(gdsTitle);
        sb.append(", gdsPrice=").append(gdsPrice);
        sb.append(", gdsPropStr=").append(gdsPropStr);
        sb.append(", gdsStock=").append(gdsStock);
        sb.append(", failReason=").append(failReason);
        sb.append("]");
        return sb.toString();
    }
}
