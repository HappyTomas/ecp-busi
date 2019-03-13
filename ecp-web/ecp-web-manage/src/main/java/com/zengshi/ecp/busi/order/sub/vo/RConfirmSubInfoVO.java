package com.zengshi.ecp.busi.order.sub.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class RConfirmSubInfoVO implements Serializable{
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    /**
	 * 子订单ID
	 */
	@NotNull
	private String orderSubId;
	/**
	 * 发货数量
	 */
	@NotNull
	private Long deliveryAmount;
	/**
	 * 子订单是否全部发货
	 */
	private Boolean isSendAll;
	/**
	 * 是否录入实体
	 */
	private String isImport;
	
	
	public String getOrderSubId() {
		return orderSubId;
	}
	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}
	public Long getDeliveryAmount() {
		return deliveryAmount;
	}
	public void setDeliveryAmount(Long deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}
	public Boolean getIsSendAll() {
		return isSendAll;
	}
	public void setIsSendAll(Boolean isSendAll) {
		this.isSendAll = isSendAll;
	}
	public String getIsImport() {
		return isImport;
	}
	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}
    
}
