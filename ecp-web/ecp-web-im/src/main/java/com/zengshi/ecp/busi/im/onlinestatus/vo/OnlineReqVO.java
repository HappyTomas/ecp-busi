package com.zengshi.ecp.busi.im.onlinestatus.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class OnlineReqVO extends EcpBasePageReqVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long hotlineId;
	
	private String csaCode;
	
	private Long shopId;
	
	private String resource;
	
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
