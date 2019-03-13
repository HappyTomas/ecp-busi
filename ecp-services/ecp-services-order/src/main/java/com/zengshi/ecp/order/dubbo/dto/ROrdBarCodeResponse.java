package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

import java.io.Serializable;

/**
 * Created by wang on 15/12/2.
 */
public class ROrdBarCodeResponse extends BaseResponseDTO{

    private static final long serialVersionUID = -6558953647655022515L;
    /**
     * 单号
     */
    String orderId;
    /**
     * 序号
      */
    Long serial;
    /**
     * 条码
      */
    String barCode;
    /**
     * 数量
      */
    Long orderAmount;
    /**
     * 定价
      */
    String basePrice;
    /**
     * 折扣
      */
    Double discount;
    String buyPrice;
    /**
     * 订单码洋
      */
    String orderMoney;
    /**
     * 订单实洋
      */
    String realMoney;
    
    /**
     * 订单码洋
      */
    String basicMoney;

    public String getBasicMoney() {
		return basicMoney;
	}

	public void setBasicMoney(String basicMoney) {
		this.basicMoney = basicMoney;
	}

	public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(String realMoney) {
        this.realMoney = realMoney;
    }
}
