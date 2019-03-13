package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromGroupDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String promTheme;

    private String promContent;

    private String status;
    
    private String statusName;

    private Date showStartTime;

    private Date showEndTime;

    private String promUrl;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    
    private Long siteId;//站点编码
    
    private Date calDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        this.promUrl = promUrl == null ? null : promUrl.trim();
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Date getCalDate() {
        return calDate;
    }

    public void setCalDate(Date calDate) {
        this.calDate = calDate;
    }

}