package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupOrdBackReqDTO extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//退货申请ID
	private Long applyId;
		
	private Long staffId;
	
	private String orderId;
	
	//子订单集合
	private List<String> subOrdList;

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public List<String> getSubOrdList() {
		return subOrdList;
	}

	public void setSubOrdList(List<String> subOrdList) {
		this.subOrdList = subOrdList;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

}
