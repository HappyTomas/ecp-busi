package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupOrdBackRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long staffId;
	//站点ID
	private Long siteId;
	
	//退货申请ID
	private Long applyId;
	
	private String orderId;
	//优惠券信息
	private List<OrdBackNumRespDTO> coupNumBeans;

	
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
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
	public List<OrdBackNumRespDTO> getCoupNumBeans() {
		return coupNumBeans;
	}
	public void setCoupNumBeans(List<OrdBackNumRespDTO> coupNumBeans) {
		this.coupNumBeans = coupNumBeans;
	}
	public Long getapplyId() {
		return applyId;
	}
	public void setapplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	
	
}
