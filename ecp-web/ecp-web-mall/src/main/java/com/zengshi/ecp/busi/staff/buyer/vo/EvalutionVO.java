package com.zengshi.ecp.busi.staff.buyer.vo;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.fastjson.annotation.JSONField;

public class EvalutionVO extends BaseResponseDTO{
	
	private static final long serialVersionUID = -8583134450100151606L;
	
	private Long skuId;
	
	private Long gdsId;
	
	private Long shopId;
	
	private String gdsName;
	
    private String orderId;

    private String orderSubId;
    
	private String url;
	
	private String skuProps;
	
	private String skuUrl;
	
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp buyTime;
    
	private int intScore;
	
	private String detail;

	
	
	public String getSkuUrl() {
		return skuUrl;
	}

	public void setSkuUrl(String skuUrl) {
		this.skuUrl = skuUrl;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSubId() {
		return orderSubId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gsdId) {
		this.gdsId = gsdId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}



	public int getIntScore() {
		return intScore;
	}

	public void setIntScore(int intScore) {
		this.intScore = intScore;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSkuProps() {
		return skuProps;
	}

	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}
    
	

}
