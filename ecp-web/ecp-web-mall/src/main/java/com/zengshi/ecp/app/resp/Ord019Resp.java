package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Ord019Resp extends IBody{
	
	private String coupCode;
	
	private String resultMsg;
	
	private String ifCanUse ="0";

	private String hashKey;
	
	private Long coupValue;

	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getIfCanUse() {
		return ifCanUse;
	}

	public void setIfCanUse(String ifCanUse) {
		this.ifCanUse = ifCanUse;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public Long getCoupValue() {
		return coupValue;
	}

	public void setCoupValue(Long coupValue) {
		this.coupValue = coupValue;
	}
    

}

