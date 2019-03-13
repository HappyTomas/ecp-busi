package com.zengshi.ecp.aip.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ExpressRssResp extends BaseInfo{

	private String result;
	private String returnCode;
	private String message;
	
	private String respParam;//回掉原始结果数据
	private String reqParam;//原始入参
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRespParam() {
		return respParam;
	}
	public void setRespParam(String respParam) {
		this.respParam = respParam;
	}
	public String getReqParam() {
		return reqParam;
	}
	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

}
