package com.zengshi.ecp.coupon.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CouponDetailPreReqDTO extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//必传字段 优惠券ID
	private Long coupId;
	//必传字段  送券数量
	private int coupSum;
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	public int getCoupSum() {
		return coupSum;
	}
	public void setCoupSum(int coupSum) {
		this.coupSum = coupSum;
	}
	
	
}
