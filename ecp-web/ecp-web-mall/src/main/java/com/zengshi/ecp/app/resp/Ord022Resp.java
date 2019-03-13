package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

import java.util.List;

public class Ord022Resp extends IBody{

	private String paraCode;
	private String paraValue;
	private String paraDesc;

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}
}
