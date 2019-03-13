package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Ord020Req  extends IBody{
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
