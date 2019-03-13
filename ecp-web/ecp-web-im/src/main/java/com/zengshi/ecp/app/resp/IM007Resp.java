package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:31  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class IM007Resp extends IBody {
	private Long count;//接入次数
	private String csaCode;//上一次接待客服账号
	private Timestamp sessionTime;//上一次会话时间
	
	public String getCsaCode() {
		return csaCode;
	}
	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}
	public Timestamp getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(Timestamp sessionTime) {
		this.sessionTime = sessionTime;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
