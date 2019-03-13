package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromInfo implements Serializable {
    private Long id;

    private Long siteId;

    private Long groupId;

    private String promTypeCode;

    private String ifShow;

    private String promClass;

    private String promTheme;

    private String promContent;

    private String promTypeShow;

    private Long priority;

    private String status;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp showStartTime;

    private Timestamp showEndTime;

    private Long shopId;

    private String promUrl;

    private String promImg;

    private String joinRange;

    private String ifMatch;

    private String ifBlacklist;

    private String ifComposit;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String keyWord;

    private String ifFreePost;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass == null ? null : promClass.trim();
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme == null ? null : promTheme.trim();
    }

    public String getPromContent() {
        return promContent;
    }

    public void setPromContent(String promContent) {
        this.promContent = promContent == null ? null : promContent.trim();
    }

    public String getPromTypeShow() {
        return promTypeShow;
    }

    public void setPromTypeShow(String promTypeShow) {
        this.promTypeShow = promTypeShow == null ? null : promTypeShow.trim();
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPromUrl() {
        return promUrl;
    }

    public void setPromUrl(String promUrl) {
        this.promUrl = promUrl == null ? null : promUrl.trim();
    }

    public String getPromImg() {
        return promImg;
    }

    public void setPromImg(String promImg) {
        this.promImg = promImg == null ? null : promImg.trim();
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

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit == null ? null : ifComposit.trim();
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getIfFreePost() {
        return ifFreePost;
    }

    public void setIfFreePost(String ifFreePost) {
        this.ifFreePost = ifFreePost == null ? null : ifFreePost.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteId=").append(siteId);
        sb.append(", groupId=").append(groupId);
        sb.append(", promTypeCode=").append(promTypeCode);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", promClass=").append(promClass);
        sb.append(", promTheme=").append(promTheme);
        sb.append(", promContent=").append(promContent);
        sb.append(", promTypeShow=").append(promTypeShow);
        sb.append(", priority=").append(priority);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", showStartTime=").append(showStartTime);
        sb.append(", showEndTime=").append(showEndTime);
        sb.append(", shopId=").append(shopId);
        sb.append(", promUrl=").append(promUrl);
        sb.append(", promImg=").append(promImg);
        sb.append(", joinRange=").append(joinRange);
        sb.append(", ifMatch=").append(ifMatch);
        sb.append(", ifBlacklist=").append(ifBlacklist);
        sb.append(", ifComposit=").append(ifComposit);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", keyWord=").append(keyWord);
        sb.append(", ifFreePost=").append(ifFreePost);
        sb.append("]");
        return sb.toString();
    }
}