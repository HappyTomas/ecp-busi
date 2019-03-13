/***********************************************************************
 * Module:  BaseBindResponse.java
 * Author:  cliff
 * Purpose: Defines the Class BaseBindResponse
 ***********************************************************************/
package com.zengshi.ecp.order.dubbo.dto.pay;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 绑卡回调应答<br>
 * Date:2015年10月10日上午10:22:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public abstract class BaseBindResponse extends BaseInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6237165106836254372L;
//	private Map<String, String> responseMap;

	public void buildSelf(final Map<String,String> responseMap) throws Exception{
		
	}
	
	public boolean verifySign(String[] signParams,String checkValue) throws Exception{
		return false;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	public  Map<String,String> toMap(){
	   	 return new HashMap<String, String>();
	    }
}