package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsSiteInfoReqDTO extends BaseInfo {

    private Long id;

    private String siteInfoName;

    private Long siteId;

    private String siteInfoType;

    private String siteInfoUrl;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private String createStaff;

    private Timestamp updateTime;

    private String updateStaff;

    private String staticId;

    private String channelId;
    
    private Long parent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteInfoName() {
        return siteInfoName;
    }

    public void setSiteInfoName(String siteInfoName) {
        this.siteInfoName = siteInfoName == null ? null : siteInfoName.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteInfoType() {
        return siteInfoType;
    }

    public void setSiteInfoType(String siteInfoType) {
        this.siteInfoType = siteInfoType == null ? null : siteInfoType.trim();
    }

    public String getSiteInfoUrl() {
        return siteInfoUrl;
    }

    public void setSiteInfoUrl(String siteInfoUrl) {
        this.siteInfoUrl = siteInfoUrl == null ? null : siteInfoUrl.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public String getStaticId() {
        return staticId;
    }

    public void setStaticId(String staticId) {
        this.staticId = staticId == null ? null : staticId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteInfoName=").append(siteInfoName);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteInfoType=").append(siteInfoType);
        sb.append(", siteInfoUrl=").append(siteInfoUrl);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", staticId=").append(staticId);
        sb.append(", parent=").append(parent);
        sb.append(", channelId=").append(channelId);
        sb.append("]");
        return sb.toString();
    }

}
