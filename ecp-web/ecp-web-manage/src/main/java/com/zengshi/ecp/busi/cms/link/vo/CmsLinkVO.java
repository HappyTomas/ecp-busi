package com.zengshi.ecp.busi.cms.link.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsLinkVO extends EcpBasePageReqVO implements Serializable{

	/**
     * 图片URL
     */
    private String vfsUrl;

    private boolean newCreate;
    
    private Long linkParent;
    
    private String mediaUuid;
    
//-----------------------------------------------------------------
    
	private Long id;

	@NotNull(message="{cms.link.linkName.notnull.err}")
	private String linkName;

	@NotNull(message="{cms.link.linkUrl.notnull.err}")
	private String linkUrl;

	@NotNull(message="{cms.link.linkType.notnull.err}")
	private String linkType;

	@NotNull(message="{cms.link.siteId.notnull.err}")
	private Long siteId;

	private Integer sortNo;

	private String status;

	@JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Timestamp createTime;

	private String createStaff;

	@JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Timestamp updateTime;

	private String updateStaff;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType == null ? null : linkType.trim();
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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

	public String getVfsUrl() {
		return vfsUrl;
	}

	public void setVfsUrl(String vfsUrl) {
		this.vfsUrl = vfsUrl;
	}

	public boolean isNewCreate() {
		return newCreate;
	}

	public void setNewCreate(boolean newCreate) {
		this.newCreate = newCreate;
	}

	public Long getLinkParent() {
		return linkParent;
	}

	public void setLinkParent(Long linkParent) {
		this.linkParent = linkParent;
	}

	public String getMediaUuid() {
		return mediaUuid;
	}

	public void setMediaUuid(String mediaUuid) {
		this.mediaUuid = mediaUuid;
	}
	
}
