package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-14下午3:54:57  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */

public class GdsMediaCategoryRespDTO extends BaseResponseDTO {


	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	private String catgCode;

	private String catgName;

	private Short catgLevel;

	private Integer sortNo;

	private String catgParent;

	private String catgUrl;

	private Long shopId;

	private String mediaId;

	private String status;

	private Timestamp createTime;

	private Long createStaff;

	private Timestamp updateTime;

	private Long updateStaff;

	private String ifLeaf;
	
	private String mediaUrl;

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode == null ? null : catgCode.trim();
	}

	public String getCatgName() {
		return catgName;
	}

	public void setCatgName(String catgName) {
		this.catgName = catgName == null ? null : catgName.trim();
	}

	public Short getCatgLevel() {
		return catgLevel;
	}

	public void setCatgLevel(Short catgLevel) {
		this.catgLevel = catgLevel;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getCatgParent() {
		return catgParent;
	}

	public void setCatgParent(String catgParent) {
		this.catgParent = catgParent == null ? null : catgParent.trim();
	}

	public String getCatgUrl() {
		return catgUrl;
	}

	public void setCatgUrl(String catgUrl) {
		this.catgUrl = catgUrl == null ? null : catgUrl.trim();
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId == null ? null : mediaId.trim();
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

	public String getIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(String ifLeaf) {
		this.ifLeaf = ifLeaf == null ? null : ifLeaf.trim();
	}
	
	

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", catgCode=").append(catgCode);
		sb.append(", catgName=").append(catgName);
		sb.append(", catgLevel=").append(catgLevel);
		sb.append(", sortNo=").append(sortNo);
		sb.append(", catgParent=").append(catgParent);
		sb.append(", catgUrl=").append(catgUrl);
		sb.append(", shopId=").append(shopId);
		sb.append(", mediaId=").append(mediaId);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", createStaff=").append(createStaff);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updateStaff=").append(updateStaff);
		sb.append(", ifLeaf=").append(ifLeaf);
		sb.append("]");
		return sb.toString();
	}
}
