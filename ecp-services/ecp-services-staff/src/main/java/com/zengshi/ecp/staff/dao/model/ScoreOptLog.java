package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScoreOptLog implements Serializable {
    private Long id;

    private Long staffId;

    private String orderId;

    private String subOrderId;

    private String optType;

    private String scoreType;

    private String scoreTypeName;

    private Long score;

    private Long totalScore;

    private Long freezeScore;

    private Long usedScore;

    private Long balanceScore;

    private String createStaff;

    private Timestamp createTime;

    private String remark;

    private Long siteId;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId == null ? null : subOrderId.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName == null ? null : scoreTypeName.trim();
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getFreezeScore() {
        return freezeScore;
    }

    public void setFreezeScore(Long freezeScore) {
        this.freezeScore = freezeScore;
    }

    public Long getUsedScore() {
        return usedScore;
    }

    public void setUsedScore(Long usedScore) {
        this.usedScore = usedScore;
    }

    public Long getBalanceScore() {
        return balanceScore;
    }

    public void setBalanceScore(Long balanceScore) {
        this.balanceScore = balanceScore;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", orderId=").append(orderId);
        sb.append(", subOrderId=").append(subOrderId);
        sb.append(", optType=").append(optType);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", scoreTypeName=").append(scoreTypeName);
        sb.append(", score=").append(score);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", freezeScore=").append(freezeScore);
        sb.append(", usedScore=").append(usedScore);
        sb.append(", balanceScore=").append(balanceScore);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", siteId=").append(siteId);
        sb.append("]");
        return sb.toString();
    }
}