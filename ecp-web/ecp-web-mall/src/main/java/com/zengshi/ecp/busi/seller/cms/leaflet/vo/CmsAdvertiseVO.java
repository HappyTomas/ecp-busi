package com.zengshi.ecp.busi.seller.cms.leaflet.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsAdvertiseVO extends EcpBasePageReqVO implements Serializable{
    
    /*--------------------------以下为model后添加的字段  start--------------------------*/
    
    private String placeName;
    
    private String shopName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startPubTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endPubTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startLostTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endLostTime;
    
    /*--------------------------以下为model后添加的字段  end--------------------------*/

    private Long id;

    @NotNull(message="{cms.advertise.advertiseTitle.null.error}")
    @Length(max=64, message="{cms.advertise.advertiseTitle.length.error}")
    private String advertiseTitle;

    @NotNull(message="{cms.advertise.linkType.null.error}")
    private String linkType;

    @NotNull(message="{cms.advertise.linkName.null.error}")
    private String linkName;

    private String linkUrl;

    private String vfsName;

    @NotNull(message="{cms.advertise.vfsId.null.error}")
    private String vfsId;

    private Integer sortNo;

    private String status;

    private String subSystem;

    private Long shopId;

    private String remark;

    @NotNull(message="{cms.advertise.placeId.null.error}")
    private Long placeId;

    private Long siteId;

    private Long templateId;
    
	private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pubTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lostTime;
    
    public Date getStartPubTime() {
        return startPubTime;
    }

    public void setStartPubTime(Date startPubTime) {
        this.startPubTime = startPubTime;
    }

    public Date getEndPubTime() {
        return endPubTime;
    }

    public void setEndPubTime(Date endPubTime) {
        this.endPubTime = endPubTime;
    }

    public Date getStartLostTime() {
        return startLostTime;
    }

    public void setStartLostTime(Date startLostTime) {
        this.startLostTime = startLostTime;
    }

    public Date getEndLostTime() {
        return endLostTime;
    }

    public void setEndLostTime(Date endLostTime) {
        this.endLostTime = endLostTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
    
    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
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
}

