package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Ord021Resp extends IBody{

	private String url;
	
	private Boolean result;
	
	private String msg;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
