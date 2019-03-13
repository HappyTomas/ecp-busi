package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.Date;
import java.util.HashMap;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class TokenRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	
	//token
	private String accessToken;
	//刷新token 如果accessToken失效 需要 该字段调用接口去刷新
	private String refreshToken;
	//失效时间 单位秒 例如10 表示产生的token10秒后失效
	private Long expiredIn;
	//刷新失效时间 单位秒 例如10 表示产生的刷新token10秒后失效
	private Long reExpiredIn;
	//失效时间  产生的时间+expiredIn
	private Date expiredTime;
	//刷新失效时间  产生的时间+reExpiredIn
	private Date reexpiredTime;
	
	//额外参数 存入
	private HashMap expandMap;
	
	//原始数据json串
	private String tokenJson;

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Long getExpiredIn() {
		return expiredIn;
	}

	public Long getReExpiredIn() {
		return reExpiredIn;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public Date getReexpiredTime() {
		return reexpiredTime;
	}

	public HashMap getExpandMap() {
		return expandMap;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setExpiredIn(Long expiredIn) {
		this.expiredIn = expiredIn;
	}

	public void setReExpiredIn(Long reExpiredIn) {
		this.reExpiredIn = reExpiredIn;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public void setReexpiredTime(Date reexpiredTime) {
		this.reexpiredTime = reexpiredTime;
	}

	public void setExpandMap(HashMap expandMap) {
		this.expandMap = expandMap;
	}

	public String getTokenJson() {
		return tokenJson;
	}

	public void setTokenJson(String tokenJson) {
		this.tokenJson = tokenJson;
	}

}

