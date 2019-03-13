package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromOrdPresent implements Serializable {
    private Long id;

    private Long relaId;

    private Long shopId;

    private String orderId;

    private String ordSubId;

    private Long promId;

    private String status;

    private String typeCode;

    private String presentValue1;

    private String presentValue2;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrdSubId() {
        return ordSubId;
    }

    public void setOrdSubId(String ordSubId) {
        this.ordSubId = ordSubId == null ? null : ordSubId.trim();
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getPresentValue1() {
        return presentValue1;
    }

    public void setPresentValue1(String presentValue1) {
        this.presentValue1 = presentValue1 == null ? null : presentValue1.trim();
    }

    public String getPresentValue2() {
        return presentValue2;
    }

    public void setPresentValue2(String presentValue2) {
        this.presentValue2 = presentValue2 == null ? null : presentValue2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", relaId=").append(relaId);
        sb.append(", shopId=").append(shopId);
        sb.append(", orderId=").append(orderId);
        sb.append(", ordSubId=").append(ordSubId);
        sb.append(", promId=").append(promId);
        sb.append(", status=").append(status);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", presentValue1=").append(presentValue1);
        sb.append(", presentValue2=").append(presentValue2);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}