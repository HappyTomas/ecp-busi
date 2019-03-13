package com.zengshi.ecp.coupon.dubbo.dto.resp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class OrdNumRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 优惠劵赠送数量
	private Long coupPresentNum;
	//优惠劵可退数量(用户剩余该小类数量)
	private Long coupBackNum;
	// 优惠券ID
	private Long coupId;
	//优惠券名称
	private String coupName;
	
	
	
	
	public String getCoupName() {
		return coupName;
	}
	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}
	public Long getCoupPresentNum() {
		return coupPresentNum;
	}
	public void setCoupPresentNum(Long coupPresentNum) {
		this.coupPresentNum = coupPresentNum;
	}
	public Long getCoupBackNum() {
		return coupBackNum;
	}
	public void setCoupBackNum(Long coupBackNum) {
		this.coupBackNum = coupBackNum;
	}
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	
	
}
