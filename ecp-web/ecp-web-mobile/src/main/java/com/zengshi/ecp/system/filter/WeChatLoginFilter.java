package com.zengshi.ecp.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.system.util.CookieUtil;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;
import com.zengshi.butterfly.core.web.WebConstants;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: 微信商城用户登录拦截器<br>
 * Date:2016年7月19日下午5:40:38  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 */
public class WeChatLoginFilter implements Filter{
	
	private static final String MODULE = WeChatLoginFilter.class.getName();
	
	 public static String[] IGNORE_SUFFIX = {};
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		//取出application-url.xml里面配置的loginPage参数，这个登录的不做拦截
		//String loginUrl = Application.getValue(WebConstants.URL_LOGIN_PAGE);
		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpServletResponse httpResponse = (HttpServletResponse)res;
		if (!shouldFilter(httpRequest)) {
	            chain.doFilter(req, res);
	            return;
	    }
		String uripath = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();//
		String skipUrl = uripath.replace(contextPath, "");
		if(uripath.indexOf("/j_spring_security_check")!=-1){
			chain.doFilter(req, res);
			return;
		}
		if(uripath.indexOf("/wx/core")!=-1){
			chain.doFilter(req, res);
			return;
		}
		if(uripath.indexOf("/login/check")!=-1){
			chain.doFilter(req, res);
			return;
		}
		//授权不用拦截
		if(uripath.indexOf("/authWechat/authRedirect")!=-1){
			chain.doFilter(req, res);
			return;
		}
		//退出登陆不用拦截
		if(uripath.indexOf("/login/unbind")!=-1){
			chain.doFilter(req, res);
			return;
		}
		//退出登陆不用拦截
		/*if(uripath.indexOf("/j_spring_security_logout")!=-1){
			chain.doFilter(req, res);
			return;
		}*/
		//LogUtil.info(MODULE, "------------------拦截url:"+uripath);
		String openId = "";
		openId = getOpenId(httpRequest);
		String sessionid = httpRequest.getSession().getId();
		if(StringUtil.isNotBlank(openId)){
	    httpRequest.getSession().setAttribute("openId", openId);
    	CookieUtil.addCookie(httpResponse, "_openId", openId, 0);
		String open = (String) CacheUtil.getItem(sessionid+"_openid");
		CustWechatRelRespDTO custRes = null;

		ICustWechatRelRSV custWechatRelRSV = ApplicationContextUtil.getBean("custWechatRelRSV", ICustWechatRelRSV.class);
		custRes = custWechatRelRSV.findCustWechatRelByWechatId(String.valueOf(openId));
		
		if (custRes != null) {
			String url = contextPath + "/j_spring_security_check?j_username=" + custRes.getStaffCode() + "&j_from=SSO";
			httpResponse.sendRedirect(url + "&Referer=" + skipUrl+"?openId="+openId);//主动请求登录权限校验，方法里面是框架层面的处理，包括用户权限及session里面设置用户信息
			return;
		   }else{
			  if(uripath.indexOf("/login")!=-1){
				   String url = httpRequest.getContextPath() +uripath+"?openId="+openId;
				   httpResponse.sendRedirect(url);
				   return;
			  }else{
				  String url = httpRequest.getContextPath() +uripath;
				   httpResponse.sendRedirect(url);
				   return;
			  }
		   }
		}else{
			openId = httpRequest.getParameter("openId");
			//进入登陆页面，若openId不为空，不用重新		
			if(StringUtil.isBlank(openId)){
				Object bopenId = httpRequest.getSession(true).getAttribute("openId");
				if(bopenId!=null){
					openId = (String)bopenId;									
				}
			}
			if(StringUtil.isBlank(openId)){
				httpRequest.setAttribute("openId", openId);
			}
			if(StringUtil.isNotBlank(openId)){
				CookieUtil.addCookie(httpResponse, "_openId", openId, 0);
			}
			//httpRequest.getSession(true).setAttribute("openId", openId);	
			//LogUtil.info(MODULE, "------------------拦截openId:"+openId);	
			/*String openid = (String) CacheUtil.getItem(sessionid);
			if(StringUtil.isNotBlank(openid)){
				ICustWechatRelRSV custWechatRelRSV = ApplicationContextUtil.getBean("custWechatRelRSV", ICustWechatRelRSV.class);
				CustWechatRelRespDTO custRes = custWechatRelRSV.findCustWechatRelByWechatId(String.valueOf(openId));
				if (custRes != null) {
					String url = contextPath + "/j_spring_security_check?j_username=" + custRes.getStaffCode() + "&j_from=SSO";
					httpResponse.sendRedirect(url + "&Referer=" + skipUrl);//主动请求登录权限校验，方法里面是框架层面的处理，包括用户权限及session里面设置用户信息
					return;
				   }else{
					   String url = httpRequest.getContextPath() +loginUrl+"?openId="+openId;
					   httpResponse.sendRedirect(url);
					   return;
				   }
			}*/
		}
	   chain.doFilter(req, res);
	
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		  String ignore_suffix = config.getInitParameter("ignore_suffix");
	        if (!"".equals(ignore_suffix))
	            IGNORE_SUFFIX = ignore_suffix.split(",");
	}

	public String getOpenId(HttpServletRequest httpRequest){
		String code = httpRequest.getParameter("code");
		Object bopenId = httpRequest.getSession().getAttribute("openId");
		if(StringUtil.isEmpty(bopenId)){
			bopenId = httpRequest.getAttribute("openId");
		}
		//LogUtil.info(MODULE, "-----------code&bopenId:"+code+"------"+bopenId);
		String openId=null;
		if(StringUtil.isEmpty(bopenId)&&StringUtil.isNotBlank(code)){
			String access_token_url = WxConstants.ACCESS_TOKEN_URL;
			access_token_url = access_token_url.replace("APPID", WxConstants.APPID);
			access_token_url = access_token_url.replace("SECRET", WxConstants.SECRET);
			access_token_url = access_token_url.replace("CODE", code);
			JSONObject jsonObject = WeixinUtil.httpRequest(access_token_url, "GET", null);
			LogUtil.info(MODULE, jsonObject.toString());
			openId = (String) jsonObject.get("openid");//微信的openId
			//String access_token = jsonObject.getString("access_token");
			
		}
		return openId;
	}
	
	  private boolean shouldFilter(HttpServletRequest request) {
	        String uri = request.getRequestURI().toLowerCase();
	        for (String suffix : IGNORE_SUFFIX) {
	            if (uri.endsWith(suffix))
	                return false;
	        }
	        return true;
	    }
	  

	    
	
}

