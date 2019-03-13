package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class ROrdSellerMsgDTO implements Serializable{

	private String orderId;

	private String sellerMsg;

	private static final long serialVersionUID = 1L;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSellerMsg() {
		return sellerMsg;
	}

	public void setSellerMsg(String sellerMsg) {
		this.sellerMsg = sellerMsg;
	}


	
}
