package com.zengshi.aip.provider.bss.model.appinfo;

import java.io.Serializable;
import java.sql.Timestamp;

public class AipAppInfoResult implements Serializable {
	
	private static final long serialVersionUID = -7985131590281837625L;

    private String appKey;

    private String appSecret;

    private String appName;

    private Timestamp registerTime;

    private String callbackUrl;

    private String appDomain;

    private String userId;

    private Short appLevel;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getAppDomain() {
		return appDomain;
	}

	public void setAppDomain(String appDomain) {
		this.appDomain = appDomain;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Short getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(Short appLevel) {
		this.appLevel = appLevel;
	}

	@Override
	public String toString() {
		return "AipAppInfoResult [appKey=" + appKey + ", appSecret="
				+ appSecret + ", appName=" + appName + ", registerTime="
				+ registerTime + ", callbackUrl=" + callbackUrl
				+ ", appDomain=" + appDomain + ", userId=" + userId
				+ ", appLevel=" + appLevel + "]";
	}

}
