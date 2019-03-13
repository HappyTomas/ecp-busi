package com.zengshi.ecp.unpf.dubbo.dto.order;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RUnpfShopExpressReq  extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private Long shopId;//店铺号
	private String platType;//平台
	
	private String code;//物流公司编码
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getPlatType() {
		return platType;
	}
	public void setPlatType(String platType) {
		this.platType = platType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
