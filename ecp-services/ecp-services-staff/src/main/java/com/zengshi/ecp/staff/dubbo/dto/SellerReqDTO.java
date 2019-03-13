package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.common.ICriteria;

public class SellerReqDTO extends BaseInfo<ICriteria> {
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 */ 
	private static final long serialVersionUID = 1L;

	/**
     * 企业Id
     */
    private Long companyId;
    
    /**
     *是否卖家标识：0否 1是 
     */
    private String shopFlag;
    
    /**
     * 用户类型:10普通 20企业 30企业管理员
     */
    private String custType;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}
}
