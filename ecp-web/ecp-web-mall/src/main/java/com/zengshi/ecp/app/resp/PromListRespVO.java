package com.zengshi.ecp.app.resp;

import java.math.BigDecimal;

import com.zengshi.butterfly.app.model.IBody;

public class PromListRespVO extends IBody{
	

	/** 
     * promId:促销编码. 
     */ 
    private Long promId;

	/** 
     * promTheme:促销名称. 
     */ 
    private String promTheme;
    

	/** 
     * skuId:单品编码. 
     */ 
    private Long skuId;
    


    

	/** 
     * discountFinalPrice:显示价格. 
     */ 
    private BigDecimal discountFinalPrice;


	public Long getPromId() {
		return promId;
	}


	public void setPromId(Long promId) {
		this.promId = promId;
	}


	public String getPromTheme() {
		return promTheme;
	}


	public void setPromTheme(String promTheme) {
		this.promTheme = promTheme;
	}


	public Long getSkuId() {
		return skuId;
	}


	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}



	public BigDecimal getDiscountFinalPrice() {
		return discountFinalPrice;
	}


	public void setDiscountFinalPrice(BigDecimal discountFinalPrice) {
		this.discountFinalPrice = discountFinalPrice;
	}

}
