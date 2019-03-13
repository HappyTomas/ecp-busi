package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsProp implements Serializable {
    private Long id;

    private String propName;

    private String propSname;

    private String propValueType;

    private String propType;

    private String propDesc;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String propInputType;

    private String propInputRule;

    private String ifAbleEdit;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getPropSname() {
        return propSname;
    }

    public void setPropSname(String propSname) {
        this.propSname = propSname == null ? null : propSname.trim();
    }

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType == null ? null : propValueType.trim();
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType == null ? null : propType.trim();
    }

    public String getPropDesc() {
        return propDesc;
    }

    public void setPropDesc(String propDesc) {
        this.propDesc = propDesc == null ? null : propDesc.trim();
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

    public String getPropInputType() {
        return propInputType;
    }

    public void setPropInputType(String propInputType) {
        this.propInputType = propInputType == null ? null : propInputType.trim();
    }

    public String getPropInputRule() {
        return propInputRule;
    }

    public void setPropInputRule(String propInputRule) {
        this.propInputRule = propInputRule == null ? null : propInputRule.trim();
    }

    public String getIfAbleEdit() {
        return ifAbleEdit;
    }

    public void setIfAbleEdit(String ifAbleEdit) {
        this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", propName=").append(propName);
        sb.append(", propSname=").append(propSname);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", propType=").append(propType);
        sb.append(", propDesc=").append(propDesc);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", propInputType=").append(propInputType);
        sb.append(", propInputRule=").append(propInputRule);
        sb.append(", ifAbleEdit=").append(ifAbleEdit);
        sb.append("]");
        return sb.toString();
    }
}
