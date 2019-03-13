package com.zengshi.ecp.busi.im.onlinestatus.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class OnlineRespVO extends EcpBasePageReqVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long hotlineId;
	
	private String csaCode;
	
	private String resource;
	
	private String onlineStatus;
	
	private String updateTime;
	
	private String createTime;


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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
