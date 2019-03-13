package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrdExpressLogReq extends BaseInfo{

    private String orderId;
    
    private Long shopId;

    private String expressCode;

    private String expressNo;

    private String expressInterface;

    private Long staffId;

    private String result;
    
    private byte[] reqParam;

    private byte[] respParam;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressInterface() {
		return expressInterface;
	}

	public void setExpressInterface(String expressInterface) {
		this.expressInterface = expressInterface;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public byte[] getReqParam() {
		return reqParam;
	}

	public void setReqParam(byte[] reqParam) {
		this.reqParam = reqParam;
	}

	public byte[] getRespParam() {
		return respParam;
	}

	public void setRespParam(byte[] respParam) {
		this.respParam = respParam;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
