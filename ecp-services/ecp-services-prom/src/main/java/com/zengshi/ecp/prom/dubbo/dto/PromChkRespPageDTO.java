package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class PromChkRespPageDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 1L;
    
    //主题属性 begin
    private Long groupId;//主题编码 对应表t_prom_group.id

    private Date showStartTimeG;//展示主题开始时间

    private Date showEndTimeG;//展示主题截止时间
    
    private String statusGroup;// 主题促销状态
    
    private String statusGroupName;// 主题促销状态
    
    private String groupName;//主题 对应表t_prom_group.promTheme
    
    private String groupContent;//促销主题内容
    
    private Long  siteId;
    
    private String siteName;
    //主题属性 end
    
    //审核 属性 begin
    private String statusChk;// 审核状态
    
    private String statusChkName;// 审核状态名称

    private String chkDesc;// 审核描述

    //审核 属性 end
    
    private Long id;//促销编码
    
    private String promTypeCode;//促销类型编码

    private String promTypeName;//促销类型名称
    
    private String promTheme;// 促销名称

    private Long shopId;// 店铺ID

    private String shopName;// 店铺名称

    private String status;// 促销状态
    
    private String statusName;// 促销状态
    
    private String ifShow;//是否展示

    private String promClass;//等级 类型
    
    private String promClassName;//名称

    private String promContent;//促销内容

    private String promTypeShow;//展示标题

    private Long priority;//优先级

    private Date startTime;//生效开始时间

    private Date endTime;//截止时间

    private Date showStartTime;//展示开始时间

    private Date showEndTime;//展示截止时间

    private String promUrl;//促销链接

    private String promImg;//促销图片编码

    private String joinRange;//参与范围

    private String ifBlacklist;//是否黑名单

    private String ifMatch;//是否单品匹配
 
    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    public String getStatusGroup() {
        return statusGroup;
    }

    public void setStatusGroup(String statusGroup) {
        this.statusGroup = statusGroup;
    }

    public String getChkDesc() {
        return chkDesc;
    }

    public void setChkDesc(String chkDesc) {
        this.chkDesc = chkDesc;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getPromTypeName() {
        return promTypeName;
    }

    public void setPromTypeName(String promTypeName) {
        this.promTypeName = promTypeName;
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

    public String getPromClassName() {
        return promClassName;
    }

    public void setPromClassName(String promClassName) {
        this.promClassName = promClassName;
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

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(String groupContent) {
        this.groupContent = groupContent;
    }

    public Date getShowStartTimeG() {
        return showStartTimeG;
    }

    public void setShowStartTimeG(Date showStartTimeG) {
        this.showStartTimeG = showStartTimeG;
    }

    public Date getShowEndTimeG() {
        return showEndTimeG;
    }

    public void setShowEndTimeG(Date showEndTimeG) {
        this.showEndTimeG = showEndTimeG;
    }

    public String getStatusChk() {
        return statusChk;
    }

    public void setStatusChk(String statusChk) {
        this.statusChk = statusChk;
    }

    public String getStatusChkName() {
        return statusChkName;
    }

    public void setStatusChkName(String statusChkName) {
        this.statusChkName = statusChkName;
    }

    public String getStatusGroupName() {
        return statusGroupName;
    }

    public void setStatusGroupName(String statusGroupName) {
        this.statusGroupName = statusGroupName;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    
}