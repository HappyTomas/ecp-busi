package com.zengshi.ecp.aip.third.service.busi.token.impl;

import java.util.HashMap;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DefaultTokenSVImpl implements ITokenSV {

	public static final String MODULE = DefaultTokenSVImpl.class.getName();

	private HashMap<String, ITokenSV> tokenSVMap;

	public HashMap<String, ITokenSV> getTokenSVMap() {
		return tokenSVMap;
	}

	public void setTokenSVMap(HashMap<String, ITokenSV> tokenSVMap) {
		this.tokenSVMap = tokenSVMap;
	}

	// 获得第三方平台token
	@Override
	public TokenRespDTO fetchToken(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		if (baseShopAuthReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (baseShopAuthReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(baseShopAuthReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		TokenRespDTO resp= this.getTokenSV(baseShopAuthReqDTO).fetchToken(
				baseShopAuthReqDTO);
		//String t="{\"taobao_user_nick\":\"sandbox_b_16\",\"re_expires_in\":15552000,\"expires_in\":12960000,\"expire_time\":1491546898910,\"r1_expires_in\":12960000,\"w2_valid\":1478588698910,\"w2_expires_in\":1800,\"taobao_user_id\":\"2076226623\",\"w1_expires_in\":12960000,\"r1_valid\":1491546898910,\"r2_valid\":1478846098910,\"w1_valid\":1491546898910,\"r2_expires_in\":259200,\"token_type\":\"Bearer\",\"refresh_token\":\"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623\",\"refresh_token_valid_time\":1494138898910,\"access_token\":\"6200623c3399a35d58e68225533178bdfc31708275c7f772076226623\"}";
		
		
		if(resp!=null && StringUtil.isNotBlank(resp.getTokenJson())){
			//加入缓存 失效时间存入
			if(resp.getExpiredIn()==null){
				CacheUtil
				.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
						+ baseShopAuthReqDTO.getShopId()
						+ baseShopAuthReqDTO.getPlatType()+baseShopAuthReqDTO.getAuthId(),resp.getTokenJson());
			}else{
				if(resp.getExpiredIn().compareTo(Long.valueOf(Integer.MAX_VALUE))>0){
					CacheUtil
					.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
							+ baseShopAuthReqDTO.getShopId()
							+ baseShopAuthReqDTO.getPlatType()+baseShopAuthReqDTO.getAuthId(),resp.getTokenJson());
				}else{
					CacheUtil
					.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
							+ baseShopAuthReqDTO.getShopId()
							+ baseShopAuthReqDTO.getPlatType()+baseShopAuthReqDTO.getAuthId(),resp.getTokenJson(),resp.getExpiredIn().intValue());
				}
			}
		}

		
		return resp;
	}

	// 获得当前shop的token
	@Override
	public String fetchShopToken(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		String tokenJson = this.fetchTokenCache(baseShopAuthReqDTO);
		JSONObject js = JSON.parseObject((String) tokenJson);
		String token = js.getString("access_token");
		return token == null ? null : token;
	}

	// 获得当前shop的token json
	@Override
	public String fetchShopTokenJson(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		String tokenJson = this.fetchTokenCache(baseShopAuthReqDTO);
		return tokenJson == null ? null : tokenJson;
	}

	// 刷新token
	@Override
	public String refreshToken(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		return this.getTokenSV(baseShopAuthReqDTO).refreshToken(
				baseShopAuthReqDTO);
	}

	// redis获得token
	private String fetchTokenCache(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {

		if (baseShopAuthReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (baseShopAuthReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(baseShopAuthReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		/*
		 * {"taobao_user_nick":"sandbox_b_16","re_expires_in":15552000,"expires_in"
		 * :
		 * 12960000,"expire_time":1491546898910,"r1_expires_in":12960000,"w2_valid"
		 * :1478588698910,"w2_expires_in":1800,"taobao_user_id":"2076226623",
		 * "w1_expires_in"
		 * :12960000,"r1_valid":1491546898910,"r2_valid":1478846098910
		 * ,"w1_valid"
		 * :1491546898910,"r2_expires_in":259200,"token_type":"Bearer"
		 * ,"refresh_token"
		 * :"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623"
		 * ,"refresh_token_valid_time":1494138898910,"access_token":
		 * "6200623c3399a35d58e68225533178bdfc31708275c7f772076226623"}
		 */
		// 存在redis token 直接返回 否则报错
		Object tokenJson = CacheUtil
				.getItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
						+ baseShopAuthReqDTO.getShopId()
						+ baseShopAuthReqDTO.getPlatType()+baseShopAuthReqDTO.getAuthId());

		if (tokenJson == null || "".equals(tokenJson)) {
			String value = "AIPTHIRD.100015";
			throw new BusinessException(value);
		}
		return tokenJson == null ? null : tokenJson.toString();
	}

	// 获得sv
	private ITokenSV getTokenSV(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		// 验证参数
		baseShopAuthReqDTO.checkParams();

		ITokenSV sv = null;
        String value="";
		if (CollectionUtils.isEmpty(tokenSVMap)) {
			value="AIPTHIRD.100009";
    		throw new BusinessException(value);
		}

		if (!tokenSVMap.containsKey(baseShopAuthReqDTO.getPlatType())) {
			value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});

		} else {
			sv = tokenSVMap.get(baseShopAuthReqDTO.getPlatType());
			if (sv == null) {
				value="AIPTHIRD.100011";
	    		throw new BusinessException(value);
			}
		}
		return sv;
	}
}
