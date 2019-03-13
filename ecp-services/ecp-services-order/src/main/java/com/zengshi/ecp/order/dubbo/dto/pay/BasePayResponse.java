package com.zengshi.ecp.order.dubbo.dto.pay;
import java.io.Serializable;
import java.util.*;

import net.sf.json.JSONObject;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付回调接口返回对象<br>
 * Date:2015年10月10日上午10:24:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public abstract class BasePayResponse implements Serializable{
	private static final long serialVersionUID = -9010102860221264246L;
	
	public void buildSelf(final Map<String,String> responseMap) throws Exception{
		
	}
	
	public boolean verifySign(String[] signParams,String checkValue) throws Exception{
		return false;
	}
	public boolean verifySign(String[] signParams,String checkValue,String signType) throws Exception{
		return false;
	}
	
	
	public String toString() {
 		return JSONObject.fromObject(this).toString();
 	}
}