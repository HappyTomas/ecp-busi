package com.zengshi.ecp.busi.goods.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsArrmsgVO extends EcpBasePageReqVO {

	private static final long serialVersionUID = -6620744971867241470L;

	private Long id;
	
	private String skuName;

	private Long skuId;

	private String operateId;

	private Long gdsId;
	
	private Long shopId;

	private String gdsName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
