package com.zengshi.ecp.coupon.dubbo.dto.resp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class OrdBackNumRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 数量
	private Long coupNum;
	// 优惠券ID
	private Long coupId;
	
	
	public Long getCoupNum() {
		return coupNum;
	}
	public void setCoupNum(Long coupNum) {
		this.coupNum = coupNum;
	}
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	
	
}
