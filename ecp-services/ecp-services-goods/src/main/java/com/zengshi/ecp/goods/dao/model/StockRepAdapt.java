package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StockRepAdapt implements Serializable {
    private Long id;

    private Long shopId;

    private Long repCode;

    private String adaptCountry;

    private String adaptProvince;

    private String adaptCity;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", repCode=").append(repCode);
        sb.append(", adaptCountry=").append(adaptCountry);
        sb.append(", adaptProvince=").append(adaptProvince);
        sb.append(", adaptCity=").append(adaptCity);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
