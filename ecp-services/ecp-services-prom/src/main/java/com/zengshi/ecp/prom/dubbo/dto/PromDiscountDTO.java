package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromDiscountDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
    private BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
    private BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价外的优惠金额 指优惠了多少金额
    private BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量
    private BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格
    private BigDecimal discountCaclPrice 	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice

    private boolean ifFulfillProm;// 是否满足促销

    private BigDecimal promId;


    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        if(discountPrice==null){
            this.discountPrice=BigDecimal.ZERO;
        }else{
            this.discountPrice = discountPrice.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        if(discountAmount==null){
            this.discountAmount=BigDecimal.ZERO;
        }else{
            this.discountAmount = discountAmount.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        if(discountMoney==null){
            this.discountMoney=BigDecimal.ZERO;
        }else{
            this.discountMoney = discountMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }
    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public BigDecimal getPromId() {
        return promId;
    }

    public void setPromId(BigDecimal promId) {
        this.promId = promId;
    }
    public BigDecimal getDiscountPriceMoney() {
        return discountPriceMoney;
    }

    public void setDiscountPriceMoney(BigDecimal discountPriceMoney) {
        this.discountPriceMoney = discountPriceMoney;
    }

	public BigDecimal getDiscountFinalPrice() {
		return discountFinalPrice;
	}

	public void setDiscountFinalPrice(BigDecimal discountFinalPrice) {
		this.discountFinalPrice = discountFinalPrice;
	}

	public BigDecimal getDiscountCaclPrice() {
		return discountCaclPrice;
	}

	public void setDiscountCaclPrice(BigDecimal discountCaclPrice) {
		this.discountCaclPrice = discountCaclPrice;
	}

}
