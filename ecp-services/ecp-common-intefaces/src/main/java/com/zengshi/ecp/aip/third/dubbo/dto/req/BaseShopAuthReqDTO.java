package com.zengshi.ecp.aip.third.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class BaseShopAuthReqDTO extends BaseInfo{

	private static final long serialVersionUID = 1L;
	
	private Long authId;//授权id
	
	private Long shopId;//店铺编码
	
	private String serverUrl;//服务链接地址
	
	private String platType;//平台类型

    private String appkey;

    private String appscret;
	
	private String accessToken;
	
	private String refreshToken;
	
	private String authUrl;//获得授权code  url 
	
	private String tokenUrl;//获得授权token url
	
	private String redirectUri;//redirect_uri指的是应用发起请求时，所传的回调地址参数，在用户授权后应用会跳转至redirect_uri。要求与应用注册时填写的回调地址域名一致或顶级域名一致 。

	private String authCode;//授权码
	
	//固定方法
	public BaseShopAuthReqDTO findBaseShopAuth(){
		return this;
	}
	//验证 基本参数
	public void checkParams() throws BusinessException{
		if(StringUtil.isEmpty(appkey)){
			throw new BusinessException("AIPTHIRD.100001");
		}
		if(StringUtil.isEmpty(appscret)){
			throw new BusinessException("AIPTHIRD.100002");
		}
		if(StringUtil.isEmpty(platType)){
			throw new BusinessException("AIPTHIRD.100003");
		}
		if(StringUtil.isEmpty(serverUrl)){
			throw new BusinessException("AIPTHIRD.100004");
		}
	}
	//验证 授权码
	public void checkAuthCode() throws BusinessException{
		if(StringUtil.isEmpty(appkey)){
			throw new BusinessException("AIPTHIRD.100001");
		}
		if(StringUtil.isEmpty(appscret)){
			throw new BusinessException("AIPTHIRD.100002");
		}
		if(StringUtil.isEmpty(platType)){
			throw new BusinessException("AIPTHIRD.100003");
		}
		if(StringUtil.isEmpty(authCode)){
			throw new BusinessException("AIPTHIRD.100005");
		}
	}
	//验证 刷新token
	public void checkRefreshToken() throws BusinessException{
		if(StringUtil.isEmpty(refreshToken)){
			throw new BusinessException("AIPTHIRD.100006");
		}
	}
	//验证 授权基本参数
	public void checkAuthParams() throws BusinessException{
		if(StringUtil.isEmpty(appkey)){
			throw new BusinessException("AIPTHIRD.100001");
		}
		if(StringUtil.isEmpty(appscret)){
			throw new BusinessException("AIPTHIRD.100002");
		}
		if(StringUtil.isEmpty(platType)){
			throw new BusinessException("AIPTHIRD.100003");
		}
		if(StringUtil.isEmpty(tokenUrl)){
			throw new BusinessException("AIPTHIRD.100007");
		}
		if(StringUtil.isEmpty(redirectUri)){
			throw new BusinessException("AIPTHIRD.100008");
		}
		if(StringUtil.isEmpty(authCode)){
			throw new BusinessException("AIPTHIRD.100006");
		}
	}
	 
	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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
		this.appkey = appkey;
	}
	public String getAppscret() {
		return appscret;
	}
	public void setAppscret(String appscret) {
		this.appscret = appscret;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}
	public Long getAuthId() {
		return authId;
	}
	public void setAuthId(Long authId) {
		this.authId = authId;
	}
}

