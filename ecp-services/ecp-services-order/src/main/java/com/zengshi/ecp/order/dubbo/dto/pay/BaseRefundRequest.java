package com.zengshi.ecp.order.dubbo.dto.pay;
import java.io.Serializable;
import java.util.*;

import net.sf.json.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 退款请求-抽象类<br>
 * Date:2015年10月10日上午10:25:42  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public abstract class BaseRefundRequest implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = -4692095639532753561L;
	private String requestUrl;
    private String charset;
    private Map<String,String> requestBody;
    
    public String buildSign(String[] signParams) {
		return null;
	}
	
    public  Map<String,String> toMap(){
   	 return new HashMap<String, String>();
    };
    
    public String toString() {
 		return JSONObject.fromObject(this).toString();
 	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Map<String, String> getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Map<String, String> requestBody) {
		this.requestBody = requestBody;
	}

}