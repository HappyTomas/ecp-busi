package com.zengshi.ecp.coupon.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupBatchFieldReqDTO extends BaseInfo{
	
	
	private static final long serialVersionUID = 1L;
	
	private String staffId;
	//0:用户类型,1:金额类型(交易金额范围,交易次数)
	private String paraType;
	//优惠券信息ID
	private Long coupId;
	//赠送张数
	private int coupNum;
	
	private String shopId;
	//交易次数
	private String dealNum;
	//交易金额起始(可为空)
	private String dealSumStart;
	//交易金额结束(可为空)
	private String dealSumEnd;
	//用户等级
	private String custLevel;
	
	
	public int getCoupNum() {
		return coupNum;
	}
	public void setCoupNum(int coupNum) {
		this.coupNum = coupNum;
	}
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getDealNum() {
		return dealNum;
	}
	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}
	public String getDealSumStart() {
		return dealSumStart;
	}
	public void setDealSumStart(String dealSumStart) {
		this.dealSumStart = dealSumStart;
	}
	public String getDealSumEnd() {
		return dealSumEnd;
	}
	public void setDealSumEnd(String dealSumEnd) {
		this.dealSumEnd = dealSumEnd;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	
	
	
	
}

