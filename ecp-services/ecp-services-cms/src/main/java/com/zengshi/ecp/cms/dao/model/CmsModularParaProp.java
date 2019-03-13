package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsModularParaProp implements Serializable {
    private Long id;

    private Long modularId;

    private String propName;

    private String propDesc;

    private String propValueType;

    private Long controlPropId;

    private String ifHaveto;

    private Integer sortNo;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String isAutobuild;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModularId() {
        return modularId;
    }

    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getPropDesc() {
        return propDesc;
    }

    public void setPropDesc(String propDesc) {
        this.propDesc = propDesc == null ? null : propDesc.trim();
    }

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType == null ? null : propValueType.trim();
    }

    public Long getControlPropId() {
        return controlPropId;
    }

    public void setControlPropId(Long controlPropId) {
        this.controlPropId = controlPropId;
    }

    public String getIfHaveto() {
        return ifHaveto;
    }

    public void setIfHaveto(String ifHaveto) {
        this.ifHaveto = ifHaveto == null ? null : ifHaveto.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getIsAutobuild() {
        return isAutobuild;
    }

    public void setIsAutobuild(String isAutobuild) {
        this.isAutobuild = isAutobuild == null ? null : isAutobuild.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modularId=").append(modularId);
        sb.append(", propName=").append(propName);
        sb.append(", propDesc=").append(propDesc);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", controlPropId=").append(controlPropId);
        sb.append(", ifHaveto=").append(ifHaveto);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isAutobuild=").append(isAutobuild);
        sb.append("]");
        return sb.toString();
    }
}
