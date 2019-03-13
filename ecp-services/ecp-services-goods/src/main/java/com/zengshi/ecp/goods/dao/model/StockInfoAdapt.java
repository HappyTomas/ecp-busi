package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class StockInfoAdapt implements Serializable {
    private Long id;

    private String catgCode;

    private Long typeId;

    private Long stockId;

    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private Long repCode;

    private String adaptCountry;

    private String adaptProvince;

    private String adaptCity;

    private String status;

    private String stockType;

    private String repType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public String getAdaptCountry() {
        return adaptCountry;
    }

    public void setAdaptCountry(String adaptCountry) {
        this.adaptCountry = adaptCountry == null ? null : adaptCountry.trim();
    }

    public String getAdaptProvince() {
        return adaptProvince;
    }

    public void setAdaptProvince(String adaptProvince) {
        this.adaptProvince = adaptProvince == null ? null : adaptProvince.trim();
    }

    public String getAdaptCity() {
        return adaptCity;
    }

    public void setAdaptCity(String adaptCity) {
        this.adaptCity = adaptCity == null ? null : adaptCity.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", typeId=").append(typeId);
        sb.append(", stockId=").append(stockId);
        sb.append(", shopId=").append(shopId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", repCode=").append(repCode);
        sb.append(", adaptCountry=").append(adaptCountry);
        sb.append(", adaptProvince=").append(adaptProvince);
        sb.append(", adaptCity=").append(adaptCity);
        sb.append(", status=").append(status);
        sb.append(", stockType=").append(stockType);
        sb.append(", repType=").append(repType);
        sb.append("]");
        return sb.toString();
    }
}
