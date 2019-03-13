package com.zengshi.ecp.unpf.dubbo.dto.auth;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-11-7 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author lisp
 */
public class UnpfShopAuthReqDTO extends BaseInfo {
	

    private Long id;

    private String platType;

    private Long shopId;

    private String appkey;

    private String appscret;

    private String status;

    private Timestamp expiredTime;

    private String serverUrl;

    private String format;

    private String signmethod;

    private String version;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String authUrl;

    private String redirectUri;

    private String authCode;

    private String accessToken;

    private String refreshToken;

    private Timestamp expiredTimeA;

    private Timestamp expiredTimeR;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getAppscret() {
        return appscret;
    }

    public void setAppscret(String appscret) {
        this.appscret = appscret == null ? null : appscret.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl == null ? null : serverUrl.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getSignmethod() {
        return signmethod;
    }

    public void setSignmethod(String signmethod) {
        this.signmethod = signmethod == null ? null : signmethod.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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

    public String getAuthUrl() {
		return authUrl;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Timestamp getExpiredTimeA() {
		return expiredTimeA;
	}

	public Timestamp getExpiredTimeR() {
		return expiredTimeR;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setExpiredTimeA(Timestamp expiredTimeA) {
		this.expiredTimeA = expiredTimeA;
	}

	public void setExpiredTimeR(Timestamp expiredTimeR) {
		this.expiredTimeR = expiredTimeR;
	}

	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("Hash = ").append(hashCode());
	        sb.append(", id=").append(id);
	        sb.append(", platType=").append(platType);
	        sb.append(", shopId=").append(shopId);
	        sb.append(", appkey=").append(appkey);
	        sb.append(", appscret=").append(appscret);
	        sb.append(", status=").append(status);
	        sb.append(", expiredTime=").append(expiredTime);
	        sb.append(", serverUrl=").append(serverUrl);
	        sb.append(", format=").append(format);
	        sb.append(", signmethod=").append(signmethod);
	        sb.append(", version=").append(version);
	        sb.append(", createTime=").append(createTime);
	        sb.append(", createStaff=").append(createStaff);
	        sb.append(", updateTime=").append(updateTime);
	        sb.append(", updateStaff=").append(updateStaff);
	        sb.append(", authUrl=").append(authUrl);
	        sb.append(", redirectUri=").append(redirectUri);
	        sb.append(", authCode=").append(authCode);
	        sb.append(", accessToken=").append(accessToken);
	        sb.append(", refreshToken=").append(refreshToken);
	        sb.append(", expiredTimeA=").append(expiredTimeA);
	        sb.append(", expiredTimeR=").append(expiredTimeR);
	        sb.append("]");
	        return sb.toString();
	    }

}
