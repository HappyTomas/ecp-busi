package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsCatlog implements Serializable {
    private Long id;

    private String catlogName;

    private String catlogCode;

    private String catlogType;

    private String catlogDesc;

    private String ifDefault;

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

    public String getCatlogName() {
        return catlogName;
    }

    public void setCatlogName(String catlogName) {
        this.catlogName = catlogName == null ? null : catlogName.trim();
    }

    public String getCatlogCode() {
        return catlogCode;
    }

    public void setCatlogCode(String catlogCode) {
        this.catlogCode = catlogCode == null ? null : catlogCode.trim();
    }

    public String getCatlogType() {
        return catlogType;
    }

    public void setCatlogType(String catlogType) {
        this.catlogType = catlogType == null ? null : catlogType.trim();
    }

    public String getCatlogDesc() {
        return catlogDesc;
    }

    public void setCatlogDesc(String catlogDesc) {
        this.catlogDesc = catlogDesc == null ? null : catlogDesc.trim();
    }

    public String getIfDefault() {
        return ifDefault;
    }

    public void setIfDefault(String ifDefault) {
        this.ifDefault = ifDefault == null ? null : ifDefault.trim();
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
        sb.append(", catlogName=").append(catlogName);
        sb.append(", catlogCode=").append(catlogCode);
        sb.append(", catlogType=").append(catlogType);
        sb.append(", catlogDesc=").append(catlogDesc);
        sb.append(", ifDefault=").append(ifDefault);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
