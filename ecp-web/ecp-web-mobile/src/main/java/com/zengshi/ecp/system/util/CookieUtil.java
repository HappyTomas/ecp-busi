package com.zengshi.ecp.system.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.zengshi.paas.utils.LogUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * cookie工具类. <br/>
 * 
 * @author lincx
 * @return
 * @since JDK 1.7
 */ 
public class CookieUtil {
	private static final String MODULE = CookieUtil.class.getName();
	/**
	  * 添加cookie
      * @param response
      * @param key cookie主键
      * @param value cookie值
      * @param maxAge 保存cookie时间，单位为秒,默认1天
      */  
	 public static void addCookie(HttpServletResponse response, String key, String value,int maxAge){
		 Cookie cookie = new Cookie(key, value);
		 if(maxAge==0 || maxAge<0){
			 maxAge=-1;
		 }
		 cookie.setPath("/");
		 cookie.setHttpOnly(true);
		 if (maxAge > 0) {
			 cookie.setMaxAge(maxAge);       
		 }
		 try {
			response.addCookie(cookie);
		} catch (Exception e) {
			LogUtil.error(MODULE,"写入cookie失败！",e);
		}
     }
	/**
	  * 删除cookie,关闭浏览器就删除该cookie信息
      * @param request
      * @param response
      * @param key cookie主键
      */  
	 public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String key){
		 Cookie cookies[] = request.getCookies();  
		 if (cookies != null) {  
			 for (int i = 0; i < cookies.length; i++) {  
				 if (cookies[i].getName().equals(key)) {  
					 Cookie cookie = new Cookie(key,null);
					 cookie.setPath("/");//设置成跟写入cookies一样的 
					 cookie.setMaxAge(0);  
					 response.addCookie(cookie);  
				 }  
			 }  
		 }  
     }
  /**
    * 取得cookie的值
 	* @param request
 	* @param key cookie主键
 	*/  
 	public static String getCookieValue(HttpServletRequest request, String key) throws UnsupportedEncodingException{
		 for(Cookie cookie : request.getCookies()){
			 if (cookie.getName().equals(key)) {   
				 return URLDecoder.decode(cookie.getValue(), "UTF-8");  
			             }  
		         }
		 return null;
  	}
 }