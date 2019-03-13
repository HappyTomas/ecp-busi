package com.zengshi.ecp.order.dubbo.dto.pay;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 抽象类--成员属性与表单域一一对应<br>
 * Date:2015年10月10日上午10:23:49  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public  class BasePayRequest extends BaseInfo{
/**
	 * 
	 */
	private static final long serialVersionUID = 4929804798688302583L;

	/**
	 * 构造签名
	 * @param signParams
	 * @return 签名
	 */
	public String buildSign(String[] signParams) {
		return null;
	}
	
     public  Map<String,String> toMap(){
    	 return new HashMap<String, String>();
     };
     
     public String toString() {
 		return JSONObject.fromObject(this).toString();
 	}
}