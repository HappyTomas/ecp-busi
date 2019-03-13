package com.zengshi.ecp.general.order.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupSkuRespDTO extends BaseResponseDTO {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;
	//购物车单例
	private Long itemId;
	
	private Long shopId;
	
	//单品ID
	private Long skuId;
	//此为单品ID的上一级商品ID,属于冗余字段,方便使用
	private Long gdsId;
	//优惠后的单价*数量的和
	private Long discountMoney;
	//购买数量
	private Long orderAmount;
	//优惠券平摊的金额
	private Long coupMoney;
	
	
	public Long getCoupMoney() {
		return coupMoney;
	}

	public void setCoupMoney(Long coupMoney) {
		this.coupMoney = coupMoney;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Long getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Long discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}
	
	
}

