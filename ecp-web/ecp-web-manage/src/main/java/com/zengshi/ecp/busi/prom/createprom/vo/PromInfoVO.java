package com.zengshi.ecp.busi.prom.createprom.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-21 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromInfoVO implements Serializable{

    /**
     * 促销基本信息vo
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long groupId;
    
    private String groupName;
    
    @NotNull(message="{prom.promInfoVO.siteId.null.error}")
    private Long siteId;
    
    @NotEmpty(message="{prom.promInfoVO.promTypeCode.null.error}")
    private String promTypeCode;

    private String ifShow;

    private String promClass;
    
    @NotEmpty(message="{prom.promInfoVO.promTheme.null.error}")
    @Length(min=0,max=60, message="{prom.promInfoVO.promTheme.length.error}")
    private String promTheme;

    @NotEmpty(message="{prom.promInfoVO.promContent.null.error}")
    private String promContent;
    
    @NotEmpty(message="{prom.promInfoVO.promTypeShow.null.error}")
    private String promTypeShow;
    
    @DecimalMin(value="0",message="{prom.promInfoVO.priority.0.error}")
    @NotNull(message="{prom.promInfoVO.priority.null.error}")
    private Long priority;

    private String status;

    @NotNull(message="{prom.promInfoVO.startTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @NotNull(message="{prom.promInfoVO.endTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //@NotNull(message="{prom.promInfoVO.showStartTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showStartTime;

    //@NotNull(message="{prom.promInfoVO.showEndTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showEndTime;
    
    @NotNull(message="{prom.promInfoVO.shopId.null.error}")
    private Long shopId;

    private String promUrl;

    private String promImg;
    
    @NotNull(message="{prom.promInfoVO.joinRange.null.error}")
    private String joinRange;

    private String ifBlacklist;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    private String ifMatch;
    
    private String keyWord;//搜索关键字
    
    private String ifFreePost;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.promTypeCode = promTypeCode;
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow;
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }

    public String getPromContent() {
        return promContent;
    }

    public void setPromContent(String promContent) {
        this.promContent = promContent;
    }

    public String getPromTypeShow() {
        return promTypeShow;
    }

    public void setPromTypeShow(String promTypeShow) {
        this.promTypeShow = promTypeShow;
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
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Date showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Date getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Date showEndTime) {
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
        this.promUrl = promUrl;
    }

    public String getPromImg() {
        return promImg;
    }

    public void setPromImg(String promImg) {
        this.promImg = promImg;
    }

    public String getJoinRange() {
        return joinRange;
    }

    public void setJoinRange(String joinRange) {
        this.joinRange = joinRange;
    }

    public String getIfBlacklist() {
        return ifBlacklist;
    }

    public void setIfBlacklist(String ifBlacklist) {
        this.ifBlacklist = ifBlacklist;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

	public String getIfFreePost() {
		return ifFreePost;
	}

	public void setIfFreePost(String ifFreePost) {
		this.ifFreePost = ifFreePost;
	}
}
