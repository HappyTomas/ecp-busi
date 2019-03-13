package com.zengshi.ecp.goods.dubbo.dto.price;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 购物车价格明细<br>
 * Date:2015年9月28日上午10:14:05 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class CartItemPriceInfo extends BaseResponseDTO {

	/**
	 * serialVersionUID:TODO(默认序列化Id).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 购物车明细Id
	 */
	private Long itemId;

	/**
	 * 单品Id
	 */
	private Long skuId;

	/**
	 * 商品Id
	 */
	private Long gdsId;

	/**
	 * 购买价格
	 */
	private Long buyPrice;

	/**
	 * 基本价格
	 */
	private Long basePrice;
	
	/**
	 * 消费积分
	 */
	private Long score;
	
	/**
	 * 购买价格类型编码
	 */
	private String priceTypeCode;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Long getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Long buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Long getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Long basePrice) {
		this.basePrice = basePrice;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}
}
