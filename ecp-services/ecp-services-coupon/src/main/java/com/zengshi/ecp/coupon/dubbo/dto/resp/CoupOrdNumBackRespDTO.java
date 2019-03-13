package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupOrdNumBackRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long staffId;
	
	private String orderId;
	//优惠券信息
	private List<OrdNumRespDTO> coupNumBeans;

	
	
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
	public List<OrdNumRespDTO> getCoupNumBeans() {
		return coupNumBeans;
	}
	public void setCoupNumBeans(List<OrdNumRespDTO> coupNumBeans) {
		this.coupNumBeans = coupNumBeans;
	}
	
	
}
