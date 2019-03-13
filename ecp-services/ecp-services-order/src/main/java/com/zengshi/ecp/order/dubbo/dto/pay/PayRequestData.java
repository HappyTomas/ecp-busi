package com.zengshi.ecp.order.dubbo.dto.pay;

import java.io.Serializable;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付请求对象<br>
 * Date:2015年10月10日上午10:21:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayRequestData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746333095421169865L;
	public static String METHOD_GET = "get";
	public static String METHOD_POST = "post";
	private Map<String,String> formData;//各通道各异的表单数据
	private String actionUrl;//请求表单URL
	private String appActionUrl;  //app的请求表单URL
	private String charset;//请求表单字符集
	private String method;//提交方式
	private String cerPassword;  //商户证书密码(支付宝SDK需要用)

	

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}


	public Map<String,String> getFormData() {
		return formData;
	}

	public void setFormData(Map<String,String> formData) {
		this.formData = formData;
	}
	public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String toString() {
 		return getClass()+" "+JSONObject.fromObject(this).toString();
 	}

	public String getCerPassword() {
		return cerPassword;
	}

	public void setCerPassword(String cerPassword) {
		this.cerPassword = cerPassword;
	}

	public String getAppActionUrl() {
		return appActionUrl;
	}

	public void setAppActionUrl(String appActionUrl) {
		this.appActionUrl = appActionUrl;
	}

    

}