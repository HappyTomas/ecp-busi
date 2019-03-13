package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScoreFuncPara implements Serializable {
    private Long paraId;

    private Short paraSeq;

    private String paraValue;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long paraId) {
        this.paraId = paraId;
    }

    public Short getParaSeq() {
        return paraSeq;
    }

    public void setParaSeq(Short paraSeq) {
        this.paraSeq = paraSeq;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue == null ? null : paraValue.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paraId=").append(paraId);
        sb.append(", paraSeq=").append(paraSeq);
        sb.append(", paraValue=").append(paraValue);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}