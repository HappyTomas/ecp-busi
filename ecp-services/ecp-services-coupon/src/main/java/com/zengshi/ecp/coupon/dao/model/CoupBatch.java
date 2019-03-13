package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupBatch implements Serializable {
    private Long id;

    private Long batchConfId;

    private Long siteId;

    private Long batchId;

    private Long coupId;

    private Long coupTypeId;

    private Long staffId;

    private String status;

    private String remark;

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

    public Long getBatchConfId() {
        return batchConfId;
    }

    public void setBatchConfId(Long batchConfId) {
        this.batchConfId = batchConfId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", batchConfId=").append(batchConfId);
        sb.append(", siteId=").append(siteId);
        sb.append(", batchId=").append(batchId);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupTypeId=").append(coupTypeId);
        sb.append(", staffId=").append(staffId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
