package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsShop2ShipmentReqDTO extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3202212290206570919L;
	private Long id;

	private Long shopId;

	// 模板id
	private Long shipmentId;

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

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

}
