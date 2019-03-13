package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustWechatRel implements Serializable {
    private String wechatId;

    private Long id;

    private Long staffId;

    private String staffCode;

    private Timestamp createTime;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
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
        sb.append(", wechatId=").append(wechatId);
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}