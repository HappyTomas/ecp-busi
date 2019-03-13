/***********************************************************************
 * Module:  BindRequestData.java
 * Author:  cliff
 * Purpose: Defines the Class BindRequestData
 ***********************************************************************/
package com.zengshi.ecp.order.dubbo.dto.pay;

import java.io.Serializable;
import java.util.Map;

import net.sf.json.JSONObject;

/** 绑定或解绑定请求数据 */
public class BindRequestData implements Serializable {
	private static final long serialVersionUID = -1829739486125511998L;
	/** 请求URL */
	private String actionUrl;
	/** 请求表单字符集 */
	private String charset;
	/** 请求参数 */
	private Map<String,String> formData;

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

	
	
	public String toString() {
 		return getClass()+" "+JSONObject.fromObject(this).toString();
 	}

	public Map<String, String> getFormData() {
		return formData;
	}

	public void setFormData(Map<String, String> formData) {
		this.formData = formData;
	}

}