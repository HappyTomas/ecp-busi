package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdProm implements Serializable {
    private Long id;

    private String orderId;

    private String orderSubId;

    private String promTypeCode;

    private String promTypeName;

    private Long promId;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private Long relaMainPromId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode == null ? null : promTypeCode.trim();
    }

    public String getPromTypeName() {
        return promTypeName;
    }

    public void setPromTypeName(String promTypeName) {
        this.promTypeName = promTypeName == null ? null : promTypeName.trim();
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
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

    public Long getRelaMainPromId() {
        return relaMainPromId;
    }

    public void setRelaMainPromId(Long relaMainPromId) {
        this.relaMainPromId = relaMainPromId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", promTypeCode=").append(promTypeCode);
        sb.append(", promTypeName=").append(promTypeName);
        sb.append(", promId=").append(promId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", relaMainPromId=").append(relaMainPromId);
        sb.append("]");
        return sb.toString();
    }
}