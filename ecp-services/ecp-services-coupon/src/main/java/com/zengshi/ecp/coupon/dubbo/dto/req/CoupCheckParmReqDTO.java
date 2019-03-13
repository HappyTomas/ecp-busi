package com.zengshi.ecp.coupon.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupCheckParmReqDTO extends BaseInfo{
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private Long coupId;
	//用户等级
	private String custLevelValue;
	//渠道来源
	private String source;
	private String ruleCode;
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	public String getCustLevelValue() {
		return custLevelValue;
	}
	public void setCustLevelValue(String custLevelValue) {
		this.custLevelValue = custLevelValue;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	
}

