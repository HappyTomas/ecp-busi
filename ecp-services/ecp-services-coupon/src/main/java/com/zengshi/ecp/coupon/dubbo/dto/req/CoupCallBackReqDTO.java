package com.zengshi.ecp.coupon.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupCallBackReqDTO extends BaseInfo{
	

	private static final long serialVersionUID = 1L;
	
	//必传字段
	private Long staffId;
	//必传字段 客户等级  01 普通会员 02 白金 03..
    private String custLevel;
    //必传字段 优惠券ID
	private Long coupId;
	//送券数量
	private int coupSum;
	
	private String orderId;
	//必传字段 0为平台级(多店铺)
	private Long shopId;
	//10:主动 :20被动
	private String coupSource;

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getCoupSource() {
		return coupSource;
	}

	public void setCoupSource(String coupSource) {
		this.coupSource = coupSource;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

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

