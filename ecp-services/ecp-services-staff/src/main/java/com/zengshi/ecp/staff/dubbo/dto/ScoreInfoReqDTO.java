package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ScoreInfoReqDTO extends BaseInfo {
    private Long id;

    private Long staffId;
    
    private String staffCode;

    private Long scoreTotal;

    private Long scoreFrozen;

    private Long scoreUsed;

    private Long scoreBalance;

    private String status;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

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

    public Long getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Long scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Long getScoreFrozen() {
        return scoreFrozen;
    }

    public void setScoreFrozen(Long scoreFrozen) {
        this.scoreFrozen = scoreFrozen;
    }

    public Long getScoreUsed() {
        return scoreUsed;
    }

    public void setScoreUsed(Long scoreUsed) {
        this.scoreUsed = scoreUsed;
    }

    public Long getScoreBalance() {
        return scoreBalance;
    }

    public void setScoreBalance(Long scoreBalance) {
        this.scoreBalance = scoreBalance;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", scoreTotal=").append(scoreTotal);
        sb.append(", scoreFrozen=").append(scoreFrozen);
        sb.append(", scoreUsed=").append(scoreUsed);
        sb.append(", scoreBalance=").append(scoreBalance);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}