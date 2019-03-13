package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdSubShare implements Serializable {
    private Long id;

    private String orderId;

    private String subOrdId;

    private Long staffId;

    private Long shareStaffId;

    private Long gdsId;

    private Timestamp createTime;

    private Long createStaff;

    private String status;

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

    public String getSubOrdId() {
        return subOrdId;
    }

    public void setSubOrdId(String subOrdId) {
        this.subOrdId = subOrdId == null ? null : subOrdId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShareStaffId() {
        return shareStaffId;
    }

    public void setShareStaffId(Long shareStaffId) {
        this.shareStaffId = shareStaffId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
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
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", subOrdId=").append(subOrdId);
        sb.append(", staffId=").append(staffId);
        sb.append(", shareStaffId=").append(shareStaffId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}