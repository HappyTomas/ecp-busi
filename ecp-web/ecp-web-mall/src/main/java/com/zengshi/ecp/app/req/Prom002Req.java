package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Prom002Req extends IBody{

	/**
	 * staffId:用户编码.
	 */
	private Long staffId;
	
	/**
	 * shopId:店铺编码.
	 */
	private Long shopId;
	
	/**
	 * gdsId:商品编码.
	 */
	private Long gdsId;
	
	/**
	 * skuId:单品编码.
	 */
	private Long skuId;
	
	/**
	 * source:来源1：WEB、2：APP、 3：其他.
	 */
	private String source;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
