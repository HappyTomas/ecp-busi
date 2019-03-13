package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-7 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromInfoResponseDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long siteId;
    
    private String siteName;//站点名称
    
    private String ifComposit;
    
    private Long groupId;
    
    private String groupName;

    private String promTypeCode;

    private String promTypeName;
    
    private String ifShow;

    private String promClass;
    
    private String promClassName;

    private String promTheme;

    private String promContent;

    private String promTypeShow;

    private Long priority;

    private String status;
    
    private String statusName;

    private Date startTime;

    private Date endTime;

    private Date showStartTime;

    private Date showEndTime;

    private Date nowTime;
    
    private Long shopId;

    private String shopName;

    private String promUrl;

    private String promImg;

    private String joinRange;

    private String ifBlacklist;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    private String ifMatch;
    
    private String nameShort;
    
    private String keyWord;//搜索关键字
    
    private String ifFreePost;//是否免邮
    
    private String ifStart="0";//活动开始标识1表示开始
    
    public String getPromTypeName() {
		return promTypeName;
	}

	public void setPromTypeName(String promTypeName) {
		this.promTypeName = promTypeName;
	}

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

    public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}

	public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

    public String getPromClassName() {
        return promClassName;
    }

    public void setPromClassName(String promClassName) {
        this.promClassName = promClassName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
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

	public String getIfStart() {
		return ifStart;
	}

	public void setIfStart(String ifStart) {
		this.ifStart = ifStart;
	}

}
