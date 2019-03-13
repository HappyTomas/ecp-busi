package com.zengshi.ecp.system.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zengshi.ecp.wxbase.servlet.CoreService;
import com.zengshi.ecp.wxbase.util.SignUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;

public class WeChatFilter implements Filter {
	
	private static final String MODULE = WeChatFilter.class.getName();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("WeChatFilter已经启动");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        Boolean isGet = request.getMethod().equals("GET");
	 
	        String path = request.getServletPath();
	        String pathInfo = path.substring(path.lastIndexOf("/"));
	 
	        if (pathInfo == null) {
	            response.getWriter().write("error");
	            System.out.println("path is null");
	        } else {
	            if (isGet) {
	                try {
						doGet(request, response);
					} catch (Exception e) {
						System.out.println("doGet is error");
					}
	            } else {
	                try {
						doPost(request, response);
					} catch (Exception e) {
						System.out.println("doPost is error");
					}
	            }
	        }
		
	}

	@Override
	public void destroy() {
		System.out.println("WeChatFilter已经销毁");
	}

	/**
	 * 对接微信公众号
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("==========================开始对接微信服务");
		  // 微信加密签名  
        String signature = req.getParameter("signature");  
        // 时间戳  
        String timestamp = req.getParameter("timestamp");  
        // 随机数  
        String nonce = req.getParameter("nonce");  
        // 随机字符串  
        String echostr = req.getParameter("echostr");  
        
        CacheUtil.addItem(WxConstants.ECP_WEIX+"signature", signature);
        CacheUtil.addItem(WxConstants.ECP_WEIX+"timestamp", timestamp);
        CacheUtil.addItem(WxConstants.ECP_WEIX+"nonce", nonce);
        CacheUtil.addItem(WxConstants.ECP_WEIX+"echostr", echostr);
  
        PrintWriter out = res.getWriter();  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr);  
        }  
        System.out.println("=============================结束对接微信服务");
        out.close();  
        out = null;  
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws Exception{
		  // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        req.setCharacterEncoding("UTF-8");  
        res.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(req);  
        // 响应消息  
        PrintWriter out = res.getWriter();  
        out.print(respMessage);  
        out.close();  
	}
}
