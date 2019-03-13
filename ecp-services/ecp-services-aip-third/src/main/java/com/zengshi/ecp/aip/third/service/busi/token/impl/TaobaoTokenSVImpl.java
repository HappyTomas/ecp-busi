package com.zengshi.ecp.aip.third.service.busi.token.impl;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.TopAuthTokenRefreshRequest;
import com.taobao.api.response.TopAuthTokenRefreshResponse;

public class TaobaoTokenSVImpl implements ITokenSV{
    
    public static final String MODULE = TaobaoTokenSVImpl.class.getName();

    @Override
    public TokenRespDTO fetchToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
    	
    	//验证参数
    	baseShopAuthReqDTO.checkAuthParams();
    	
        String url=baseShopAuthReqDTO.getTokenUrl();  
        Map<String,String> props=new HashMap<String,String>();  
        props.put("grant_type","authorization_code");  
         
        props.put("code",baseShopAuthReqDTO.getAuthCode());  
        props.put("client_id",baseShopAuthReqDTO.getAppkey());  
        props.put("client_secret",baseShopAuthReqDTO.getAppscret());  
        props.put("redirect_uri",baseShopAuthReqDTO.getRedirectUri());  
        props.put("view","web");  
        String s="";  
    	TokenRespDTO resp=null;
        try{
        	s=WebUtils.doPost(url, props, 30000, 30000);  
        	LogUtil.info(MODULE, s);
			if(StringUtil.isNotBlank(s)){
				resp=new TokenRespDTO();
				//String t="{\"taobao_user_nick\":\"sandbox_b_16\",\"re_expires_in\":15552000,\"expires_in\":12960000,\"expire_time\":1491546898910,\"r1_expires_in\":12960000,\"w2_valid\":1478588698910,\"w2_expires_in\":1800,\"taobao_user_id\":\"2076226623\",\"w1_expires_in\":12960000,\"r1_valid\":1491546898910,\"r2_valid\":1478846098910,\"w1_valid\":1491546898910,\"r2_expires_in\":259200,\"token_type\":\"Bearer\",\"refresh_token\":\"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623\",\"refresh_token_valid_time\":1494138898910,\"access_token\":\"6200623c3399a35d58e68225533178bdfc31708275c7f772076226623\"}";
				JSONObject js = JSON.parseObject((String) s);
				if(StringUtil.isBlank(js.getString("error"))){
					String token = js.getString("access_token");
					Long expiredIn=js.getLong("expires_in");
					Long reExpiredIn=js.getLong("re_expires_in");
					String refreshToken=js.getString("refresh_token");
					Date expiredTime=js.getDate("expire_time");
					Date reexpiredTime=js.getDate("refresh_token_valid_time");
					resp.setAccessToken(token);
					resp.setExpiredIn(expiredIn);
					resp.setExpiredTime(expiredTime);
					resp.setReExpiredIn(reExpiredIn);
					resp.setReexpiredTime(reexpiredTime);
					resp.setRefreshToken(refreshToken);
					resp.setTokenJson(s);
				}else{
					 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{s});
				}
			}
        }catch(IOException e){
        	LogUtil.error(MODULE, e.toString());
        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }catch(Exception e){
        	LogUtil.error(MODULE, e.toString());
        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }    
    	
        return resp;
    	
    	/*baseShopAuthReqDTO.checkAuthCode();
    	
    	TaobaoClient client = new DefaultTaobaoClient(baseShopAuthReqDTO.getServerUrl(), baseShopAuthReqDTO.getAppKey(), baseShopAuthReqDTO.getAppScret());
    	TopAuthTokenCreateRequest req = new TopAuthTokenCreateRequest();
    	req.setCode(baseShopAuthReqDTO.getAuthCode());
    	req.setUuid( UUID.randomUUID().toString());
    	TopAuthTokenCreateResponse rsp;
    	TokenRespDTO resp=null;
    	try {
			rsp = client.execute(req);
			if(StringUtil.isNotBlank(rsp.getErrorCode())){
				throw new BusinessException(rsp.getErrorCode()+rsp.getMsg());
			}
			
			if(StringUtil.isNotBlank(rsp.getBody())){
				resp=new TokenRespDTO();
				//String t="{\"taobao_user_nick\":\"sandbox_b_16\",\"re_expires_in\":15552000,\"expires_in\":12960000,\"expire_time\":1491546898910,\"r1_expires_in\":12960000,\"w2_valid\":1478588698910,\"w2_expires_in\":1800,\"taobao_user_id\":\"2076226623\",\"w1_expires_in\":12960000,\"r1_valid\":1491546898910,\"r2_valid\":1478846098910,\"w1_valid\":1491546898910,\"r2_expires_in\":259200,\"token_type\":\"Bearer\",\"refresh_token\":\"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623\",\"refresh_token_valid_time\":1494138898910,\"access_token\":\"6200623c3399a35d58e68225533178bdfc31708275c7f772076226623\"}";
				JSONObject js = JSON.parseObject((String) rsp.getBody());
				String token = js.getString("access_token");
				Long expiredIn=js.getLong("expires_in");
				Long reExpiredIn=js.getLong("re_expires_in");
				String refreshToken=js.getString("refresh_token");
				Date expiredTime=js.getDate("expire_time");
				Date reexpiredTime=js.getDate("refresh_token_valid_time");
				
				resp.setAccessToken(token);
				resp.setExpiredIn(expiredIn);
				resp.setExpiredTime(expiredTime);
				resp.setReExpiredIn(reExpiredIn);
				resp.setReexpiredTime(reexpiredTime);
				resp.setRefreshToken(refreshToken);
				resp.setTokenJson(rsp.getBody());
			}
		} catch (ApiException e) {
			 LogUtil.error(MODULE, e.toString());
			 throw new BusinessException(e.toString());
		}catch (Exception e) {
			 LogUtil.error(MODULE, e.toString());
			 throw new BusinessException(e.toString());
		}
    	 return resp;*/
    }
 /*   refresh_token主要用来刷新sessionkey有效时间的，

    对于正式环境测试状态的应用，我们限制了sessionkey和access_token的有效时间都是24小时，的时间是固定的，不会因为刷新而延长，所以出现了这个问题。

    对于上线的在线订购和店铺模块应用，sessionkey有效时间和订购关系绑定，不需要刷新有效时间。但是对与安全等级为2的应用，可以用于刷新r2的授权时间，可以参考文档  http://open.taobao.com/doc/detail.htm?id=1002 。

    对于商家后台系统类型的应用，sessionkey也是都是固定时长的，不需要刷新。*/
    @Override
	 public String refreshToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{

    	//验证 刷新token是否存在
    	baseShopAuthReqDTO.checkRefreshToken();
    	
    	TaobaoClient client = new DefaultTaobaoClient(baseShopAuthReqDTO.getServerUrl(), baseShopAuthReqDTO.getAppkey(), baseShopAuthReqDTO.getAppscret());
    	TopAuthTokenRefreshRequest req = new TopAuthTokenRefreshRequest();
    	req.setRefreshToken(baseShopAuthReqDTO.getRefreshToken());
    	TopAuthTokenRefreshResponse rsp;
		try {
			rsp = client.execute(req);
			if(StringUtil.isNotBlank(rsp.getErrorCode())){
				throw new BusinessException(rsp.getErrorCode()+rsp.getMsg());
			}
		} catch (ApiException e) {
			 LogUtil.error(MODULE, e.toString());
			 throw new BusinessException(e.getErrCode() + e.getErrMsg());
		}catch (Exception e) {
			 LogUtil.error(MODULE, e.toString());
			 throw new BusinessException(e.toString());
		}
		 return rsp==null?"":rsp.getTokenResult();
	 }
	//获得当前shop的token
	@Override
	public String fetchShopToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return "";
    }
	//获得当前shop的token json
	@Override
	public String fetchShopTokenJson(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		 return "";
    }
}

