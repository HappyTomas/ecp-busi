package com.zengshi.ecp.busi.im.serv.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class HotlineInfoReqVO extends EcpBaseResponseVO {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 */ 
	private static final long serialVersionUID = -4225295685809294733L;
	
	private String csaCode;
	
	private Long shopId;

	public String getCsaCode() {
		return csaCode;
	}

	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
}
