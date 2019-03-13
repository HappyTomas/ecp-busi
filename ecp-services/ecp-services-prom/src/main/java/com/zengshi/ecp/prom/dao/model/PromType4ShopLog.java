package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromType4ShopLog implements Serializable {
    private Long id;

    private Long type4shopId;

    private Long shopId;

    private String promTypeCode;

    private String status;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp createTimeLog;

    private Long createStaffLog;

    private Timestamp createTime;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType4shopId() {
        return type4shopId;
    }

    public void setType4shopId(Long type4shopId) {
        this.type4shopId = type4shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode == null ? null : promTypeCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreateTimeLog() {
        return createTimeLog;
    }

    public void setCreateTimeLog(Timestamp createTimeLog) {
        this.createTimeLog = createTimeLog;
    }

    public Long getCreateStaffLog() {
        return createStaffLog;
    }

    public void setCreateStaffLog(Long createStaffLog) {
        this.createStaffLog = createStaffLog;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type4shopId=").append(type4shopId);
        sb.append(", shopId=").append(shopId);
        sb.append(", promTypeCode=").append(promTypeCode);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTimeLog=").append(createTimeLog);
        sb.append(", createStaffLog=").append(createStaffLog);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}