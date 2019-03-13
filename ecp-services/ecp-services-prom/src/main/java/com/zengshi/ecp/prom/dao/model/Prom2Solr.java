package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Prom2Solr implements Serializable {
    private Long id;

    private Long promId;

    private Long siteId;

    private Long shopId;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp showStartTime;

    private Timestamp showEndTime;

    private String status;

    private String promTypeCode;

    private String ifShow;

    private String joinRange;

    private String ifMatch;

    private String ifBlacklist;

    private String sendStatus;

    private String solrType;

    private String sendType;

    private Timestamp sendTime;

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

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Timestamp showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Timestamp getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Timestamp showEndTime) {
        this.showEndTime = showEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode == null ? null : promTypeCode.trim();
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
    }

    public String getJoinRange() {
        return joinRange;
    }

    public void setJoinRange(String joinRange) {
        this.joinRange = joinRange == null ? null : joinRange.trim();
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch == null ? null : ifMatch.trim();
    }

    public String getIfBlacklist() {
        return ifBlacklist;
    }

    public void setIfBlacklist(String ifBlacklist) {
        this.ifBlacklist = ifBlacklist == null ? null : ifBlacklist.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getSolrType() {
        return solrType;
    }

    public void setSolrType(String solrType) {
        this.solrType = solrType == null ? null : solrType.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
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
        sb.append(", promId=").append(promId);
        sb.append(", siteId=").append(siteId);
        sb.append(", shopId=").append(shopId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", showStartTime=").append(showStartTime);
        sb.append(", showEndTime=").append(showEndTime);
        sb.append(", status=").append(status);
        sb.append(", promTypeCode=").append(promTypeCode);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", joinRange=").append(joinRange);
        sb.append(", ifMatch=").append(ifMatch);
        sb.append(", ifBlacklist=").append(ifBlacklist);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", solrType=").append(solrType);
        sb.append(", sendType=").append(sendType);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}