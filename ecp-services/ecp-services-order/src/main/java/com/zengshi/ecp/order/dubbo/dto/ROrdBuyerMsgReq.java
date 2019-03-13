package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdBuyerMsgReq extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String buyMsg;
	
	private String orderId;

	public String getBuyMsg() {
		return buyMsg;
	}

	public void setBuyMsg(String buyMsg) {
		this.buyMsg = buyMsg;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
