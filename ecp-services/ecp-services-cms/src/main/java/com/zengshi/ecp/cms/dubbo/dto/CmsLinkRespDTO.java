package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsLinkRespDTO  extends BaseResponseDTO{

/*--------------------------以下为model后添加的字段 start--------------------------*/
    
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 站点翻译
     */
    private String siteZH;
    //链接类型
    private String linkTypeZH;
    
    List<CmsLinkRespDTO> childList;
    
    /*--------------------------以下为model后添加的字段 end------------------------*/
	private Long id;

	private String linkName;

	private String linkUrl;

	private String linkType;

	private Long siteId;

	private Integer sortNo;

	private String status;

	private Timestamp createTime;

	private String createStaff;

	private Timestamp updateTime;

	private String updateStaff;

	private Long linkParent;

	private Long linkLevel;

	private Long ifLeaf;

	private String mediaUuid;

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

	public String getStatusZH() {
		return statusZH;
	}

	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
	}

	public String getSiteZH() {
		return siteZH;
	}

	public void setSiteZH(String siteZH) {
		this.siteZH = siteZH;
	}

	public String getLinkTypeZH() {
		return linkTypeZH;
	}

	public void setLinkTypeZH(String linkTypeZH) {
		this.linkTypeZH = linkTypeZH;
	}

	public Long getLinkParent() {
		return linkParent;
	}

	public void setLinkParent(Long linkParent) {
		this.linkParent = linkParent;
	}

	public Long getLinkLevel() {
		return linkLevel;
	}

	public void setLinkLevel(Long linkLevel) {
		this.linkLevel = linkLevel;
	}

	public Long getIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(Long ifLeaf) {
		this.ifLeaf = ifLeaf;
	}

	public String getMediaUuid() {
		return mediaUuid;
	}

	public void setMediaUuid(String mediaUuid) {
		this.mediaUuid = mediaUuid;
	}

    public List<CmsLinkRespDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<CmsLinkRespDTO> childList) {
        this.childList = childList;
    }
	
}
