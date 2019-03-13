package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrderDetialPrintReq extends BaseInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderIds;

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
}
