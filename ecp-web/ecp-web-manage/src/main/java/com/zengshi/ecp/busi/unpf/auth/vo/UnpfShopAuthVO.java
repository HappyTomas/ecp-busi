package com.zengshi.ecp.busi.unpf.auth.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
* @author  lisp: 
* @date 创建时间：2016年11月8日 下午2:55:56 
* @version 1.0 
* */
public class UnpfShopAuthVO extends EcpBasePageReqVO {

	private static final long serialVersionUID = 1L;

	 private Long id;

    private String platType;

    private Long shopId;

    @NotEmpty(message="{unpf.UnpfShopAuthVO.appkey.null.error}")
    private String appkey;

    @NotEmpty(message="{unpf.UnpfShopAuthVO.appscret.null.error}")
    private String appscret;

    private String status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    @NotEmpty(message="{unpf.UnpfShopAuthVO.serverUrl.null.error}")
    private String serverUrl;

    private String format;

    private String signmethod;

    private String version;
    
    private String authUrl;

    private String redirectUri;

	public Long getId() {
		return id;
	}

	public String getPlatType() {
		return platType;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getAppkey() {
		return appkey;
	}

	public String getAppscret() {
		return appscret;
	}

	public String getStatus() {
		return status;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getFormat() {
		return format;
	}

	public String getSignmethod() {
		return signmethod;
	}

	public String getVersion() {
		return version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public void setAppscret(String appscret) {
		this.appscret = appscret;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setSignmethod(String signmethod) {
		this.signmethod = signmethod;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

}


