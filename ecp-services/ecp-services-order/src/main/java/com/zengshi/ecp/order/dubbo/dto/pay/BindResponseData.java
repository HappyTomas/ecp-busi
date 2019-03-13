/***********************************************************************
 * Module:  BaseBindResponseData.java
 * Author:  cliff
 * Purpose: Defines the Class BaseBindResponseData
 ***********************************************************************/
package com.zengshi.ecp.order.dubbo.dto.pay;
import java.io.Serializable;
import java.util.*;

import net.sf.json.JSONObject;

/** 绑定或解绑定响应数据对象 */
public  class BindResponseData implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 6078610890534403532L;
	private BaseBindResponse response;
	public BaseBindResponse getResponse() {
		return response;
	}
	public void setResponse(BaseBindResponse response) {
		this.response = response;
	}
	public String toString() {
 		return JSONObject.fromObject(this).toString();
 	}

}