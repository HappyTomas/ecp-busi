package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Prom003Req extends IBody{
	
	/**
	 * staffId:用户id.
	 */
	private Long staffId;


	/**
	 * gdsId:商品编码.
	 */
	private Long gdsId;
	
	/**
	 * skuId:单品编码.
	 */
	private Long skuId;
	
	/**
	 * shopId:购物编码.
	 */
	private Long shopId;
	
	/**
	 * source:来源 1：WEB、2：APP、 3：其他.
	 */
	private String source;
	
	/**
	 * matchType:搭配 1： 自由搭配 、 2：固定搭配.
	 */
	private String matchType;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
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

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
	

}
