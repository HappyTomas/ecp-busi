package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrdSubShareReq extends BaseInfo{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -6284023350317186909L;

	private String orderId;
	 
	 private String status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
