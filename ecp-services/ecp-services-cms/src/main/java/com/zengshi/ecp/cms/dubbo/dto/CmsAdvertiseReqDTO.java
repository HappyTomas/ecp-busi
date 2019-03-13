package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 作为请求参数类<br>
 * Date:2015年8月7日下午5:07:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsAdvertiseReqDTO extends BaseInfo {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    
    private Timestamp startPubTime;
    
    private Timestamp endPubTime;
    
    private Timestamp startLostTime;
    
    private Timestamp endLostTime;
    
    private Timestamp thisTime;//当前时间
    
    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private String advertiseTitle;

    private String linkType;

    private String linkName;

    private String linkUrl;

    private String vfsName;

    private String vfsId;

    private Integer sortNo;

    private String status;

    private String subSystem;

    private Timestamp pubTime;

    private Timestamp lostTime;

    private Long shopId;

    private Long siteId;

    private Long templateId;

    private Long placeId;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;
    
    private String platformType;

    /**
     * 缩略图文件附件系统ID(文件附件模块ID)
     */
    private String nailVfsId;
    
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertiseTitle() {
        return advertiseTitle;
    }

    public void setAdvertiseTitle(String advertiseTitle) {
        this.advertiseTitle = advertiseTitle == null ? null : advertiseTitle.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getVfsName() {
        return vfsName;
    }

    public void setVfsName(String vfsName) {
        this.vfsName = vfsName == null ? null : vfsName.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
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

    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem == null ? null : subSystem.trim();
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public Timestamp getLostTime() {
        return lostTime;
    }

    public void setLostTime(Timestamp lostTime) {
        this.lostTime = lostTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
    
    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType== null ? null : platformType.trim();
    }

    public String getNailVfsId() {
        return nailVfsId;
    }

    public void setNailVfsId(String nailVfsId) {
        this.nailVfsId = nailVfsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", advertiseTitle=").append(advertiseTitle);
        sb.append(", linkType=").append(linkType);
        sb.append(", linkName=").append(linkName);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", vfsName=").append(vfsName);
        sb.append(", vfsId=").append(vfsId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", subSystem=").append(subSystem);
        sb.append(", pubTime=").append(pubTime);
        sb.append(", lostTime=").append(lostTime);
        sb.append(", shopId=").append(shopId);
        sb.append(", siteId=").append(siteId);
        sb.append(", templateId=").append(templateId);
        sb.append(", placeId=").append(placeId);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", platformType=").append(platformType);
        sb.append(", nailVfsId=").append(nailVfsId);
        sb.append("]");
        return sb.toString();
    }
    
    public Timestamp getStartPubTime() {
        return startPubTime;
    }

    public void setStartPubTime(Timestamp startPubTime) {
        this.startPubTime = startPubTime;
    }

    public Timestamp getEndPubTime() {
        return endPubTime;
    }

    public void setEndPubTime(Timestamp endPubTime) {
        this.endPubTime = endPubTime;
    }

    public Timestamp getStartLostTime() {
        return startLostTime;
    }

    public void setStartLostTime(Timestamp startLostTime) {
        this.startLostTime = startLostTime;
    }

    public Timestamp getEndLostTime() {
        return endLostTime;
    }

    public void setEndLostTime(Timestamp endLostTime) {
        this.endLostTime = endLostTime;
    }

    public Timestamp getThisTime() {
        return thisTime;
    }

    public void setThisTime(Timestamp thisTime) {
        this.thisTime = thisTime;
    }

}

