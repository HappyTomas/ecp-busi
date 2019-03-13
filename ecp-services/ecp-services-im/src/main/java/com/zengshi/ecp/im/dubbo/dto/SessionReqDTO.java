package com.zengshi.ecp.im.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SessionReqDTO extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String csaCode;
	
	private String userCode;
	
	private Long shopId;
	
	private String gdsId;
	
	private String ordId;
	
	private String issueType;
	
	private Long reqTime;
	
	private String status;
	
	private Timestamp sessionTime;
	
	public Long getReqTime() {
		return reqTime;
	}

	public void setReqTime(Long reqTime) {
		this.reqTime = reqTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCsaCode() {
		return csaCode;
	}

	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getGdsId() {
		return gdsId;
	}

	public void setGdsId(String gdsId) {
		this.gdsId = gdsId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Timestamp sessionTime) {
		this.sessionTime = sessionTime;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
