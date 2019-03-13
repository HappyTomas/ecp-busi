/**
 * 
 */
package com.zengshi.ecp.busi.order;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panjs
 * 针对 request提供的一些额外的Util方法；
 */
public class RequestUtil {
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		
		String requestedWith = request.getHeader("x-requested-with"); 
        // 表示是一个AJAX POST请求
        if ("XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
        	return true;
        }
        return false;
	}

}
