package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrdCartReqVO extends EcpBasePageReqVO implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -5511283983579724243L;
	private Long id;
	private String cartType;
	private Long staffId;
	private Long skuId;
	private Long amount;
	private Long scoreTypeId;
	private Long shopId;
	private String shopName;
	private Long promId;

	private String ordCartItemList;

	public String getCartType() {
		return cartType;
	}

	public void setCartType(String cartType) {
		this.cartType = cartType;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getPromId() {
		return promId;
	}

	public void setPromId(Long promId) {
		this.promId = promId;
	}

	public String getOrdCartItemList() {
		return ordCartItemList;
	}

	public void setOrdCartItemList(String ordCartItemList) {
		this.ordCartItemList = ordCartItemList;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getScoreTypeId() {
		return scoreTypeId;
	}

	public void setScoreTypeId(Long scoreTypeId) {
		this.scoreTypeId = scoreTypeId;
	}

}
