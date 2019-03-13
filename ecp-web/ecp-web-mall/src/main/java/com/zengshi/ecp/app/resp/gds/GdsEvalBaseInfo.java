package com.zengshi.ecp.app.resp.gds;

import java.sql.Timestamp;

import com.zengshi.butterfly.app.model.IBody;

public class GdsEvalBaseInfo extends IBody{
	public Long getSkuId() {
		return skuId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getGdsName() {
		return gdsName;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getOrderSubId() {
		return orderSubId;
	}

	public String getUrl() {
		return url;
	}

	public String getSkuProps() {
		return skuProps;
	}

	public String getSkuUrl() {
		return skuUrl;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public int getIntScore() {
		return intScore;
	}

	public String getDetail() {
		return detail;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}

	public void setSkuUrl(String skuUrl) {
		this.skuUrl = skuUrl;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public void setIntScore(int intScore) {
		this.intScore = intScore;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	private Long skuId;
	
	private Long gdsId;
	
	private Long shopId;
	
	private String gdsName;
	
    private String orderId;

    private String orderSubId;
    
	private String url;
	
	private String skuProps;
	
	private String skuUrl;
	
	   private Timestamp buyTime;
	    
		private int intScore;
		
		private String detail;
}

