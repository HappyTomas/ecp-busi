package com.zengshi.ecp.order.dubbo.dto.pay;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 绑定或解绑定请求对象<br>
 * Date:2015年10月10日上午10:22:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public  class BaseBindRequest extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4737148080328112873L;
	
	
	/**
	 * 构造签名，请在子类重新实现此方法
	 * @param signParams
	 * @return 签名
	 */
	public String buildSign(String[] signParams) {
		return null;
	}
	
	
	/**
	 * 请在子类重新实现此方法
	 * @return
	 */
     //public  abstract Map<String,String> toMap();
	public  Map<String,String> toMap(){
   	 return new HashMap<String, String>();
    }
    
	public String toString() {
 		return JSONObject.fromObject(this).toString();
 	}
}