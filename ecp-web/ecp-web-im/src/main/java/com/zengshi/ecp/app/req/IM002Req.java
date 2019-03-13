package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class IM002Req extends IBody {
	private Long shopId;
	private String userCode;
	private String csaCode;
	private String sessionId;
	private String satisfyType;
	private String notSatisfyType;
	private String notSatisfyReason;
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCsaCode() {
		return csaCode;
	}
	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSatisfyType() {
		return satisfyType;
	}
	public void setSatisfyType(String satisfyType) {
		this.satisfyType = satisfyType;
	}
	public String getNotSatisfyType() {
		return notSatisfyType;
	}
	public void setNotSatisfyType(String notSatisfyType) {
		this.notSatisfyType = notSatisfyType;
	}
	public String getNotSatisfyReason() {
		return notSatisfyReason;
	}
	public void setNotSatisfyReason(String notSatisfyReason) {
		this.notSatisfyReason = notSatisfyReason;
	}

}
