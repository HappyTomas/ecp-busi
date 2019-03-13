package com.zengshi.ecp.im.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OnlineReqDTO extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long hotlineId;
	
	private String csaCode;
	
	private String resource;
	
	private Long shopId;
	
	private String onlineStatus;
	
	private Timestamp updateTime;
	
	private Timestamp createTime;


	public Long getHotlineId() {
		return hotlineId;
	}

	public void setHotlineId(Long hotlineId) {
		this.hotlineId = hotlineId;
	}

	public String getCsaCode() {
		return csaCode;
	}

	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
}
