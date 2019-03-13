package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CouponPresentReqDTO extends BaseInfo{
	
	private static final long serialVersionUID = 1L;

	private Long staffId;
	
	private String orderId;
	
	private Long shopId;
	
	//客户等级  01 普通会员 02 白金 03..
    private String custLevel;
	
	//渠道来源
	private String source;
	
	//优惠券信息
	private List<CouponDetailPreReqDTO> couponDetailPreBeans;

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public List<CouponDetailPreReqDTO> getCouponDetailPreBeans() {
		return couponDetailPreBeans;
	}

	public void setCouponDetailPreBeans(List<CouponDetailPreReqDTO> couponDetailPreBeans) {
		this.couponDetailPreBeans = couponDetailPreBeans;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
