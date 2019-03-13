package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScoreImportTemp implements Serializable {
    private Integer id;

    private Long staffId;

    private Integer impScore;

    private String remark;

    private String errorMess;

    private Timestamp createTime;

    private Long createStaff;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Integer getImpScore() {
        return impScore;
    }

    public void setImpScore(Integer impScore) {
        this.impScore = impScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getErrorMess() {
        return errorMess;
    }

    public void setErrorMess(String errorMess) {
        this.errorMess = errorMess == null ? null : errorMess.trim();
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
        sb.append(", staffId=").append(staffId);
        sb.append(", impScore=").append(impScore);
        sb.append(", remark=").append(remark);
        sb.append(", errorMess=").append(errorMess);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append("]");
        return sb.toString();
    }
}