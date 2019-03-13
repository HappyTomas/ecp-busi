package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScoreDetailSel implements Serializable {
    private Long id;

    private Long staffId;

    private String orderId;

    private String scoreTypeOrig;

    private String scoreType;

    private Timestamp createTime;

    private Long consumeScore;

    private Long modifyAddScore;

    private Long exchangeScore;

    private Long modifyReduceScore;

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

    public String getScoreTypeOrig() {
        return scoreTypeOrig;
    }

    public void setScoreTypeOrig(String scoreTypeOrig) {
        this.scoreTypeOrig = scoreTypeOrig == null ? null : scoreTypeOrig.trim();
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getConsumeScore() {
        return consumeScore;
    }

    public void setConsumeScore(Long consumeScore) {
        this.consumeScore = consumeScore;
    }

    public Long getModifyAddScore() {
        return modifyAddScore;
    }

    public void setModifyAddScore(Long modifyAddScore) {
        this.modifyAddScore = modifyAddScore;
    }

    public Long getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Long exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Long getModifyReduceScore() {
        return modifyReduceScore;
    }

    public void setModifyReduceScore(Long modifyReduceScore) {
        this.modifyReduceScore = modifyReduceScore;
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
        sb.append(", scoreTypeOrig=").append(scoreTypeOrig);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", createTime=").append(createTime);
        sb.append(", consumeScore=").append(consumeScore);
        sb.append(", modifyAddScore=").append(modifyAddScore);
        sb.append(", exchangeScore=").append(exchangeScore);
        sb.append(", modifyReduceScore=").append(modifyReduceScore);
        sb.append(", remark=").append(remark);
        sb.append(", siteId=").append(siteId);
        sb.append("]");
        return sb.toString();
    }
}