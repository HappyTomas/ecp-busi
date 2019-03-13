package com.zengshi.ecp.app.resp;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.butterfly.app.model.IBody;

public class PromInfoRespVO extends IBody{
	
	/** 
     * id:促销编码. 
     */ 
    private Long id;
	
	/** 
     * nameShort:促销类型简称. 
     */ 
    private String nameShort;
    
	/** 
     * promTheme:促销名称. 
     */ 
    private String promTheme;
    
	/** 
     * promContent:促销内容. 
     */ 
    private String promContent;
    
    /** 
     * discountCaclPrice:显示价格. 
     */ 
    private BigDecimal discountCaclPrice 	= BigDecimal.ZERO;
    
    /** 
     * discountFinalPrice:划掉价格. 
     */ 
    private BigDecimal discountFinalPrice 	= BigDecimal.ZERO;

	private Timestamp startTime;
	
	private Timestamp endTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public String getPromTheme() {
		return promTheme;
	}

	public void setPromTheme(String promTheme) {
		this.promTheme = promTheme;
	}

	public String getPromContent() {
		return promContent;
	}

	public void setPromContent(String promContent) {
		this.promContent = promContent;
	}

	public BigDecimal getDiscountCaclPrice() {
		return discountCaclPrice;
	}

	public void setDiscountCaclPrice(BigDecimal discountCaclPrice) {
		this.discountCaclPrice = discountCaclPrice;
	}

	public BigDecimal getDiscountFinalPrice() {
		return discountFinalPrice;
	}

	public void setDiscountFinalPrice(BigDecimal discountFinalPrice) {
		this.discountFinalPrice = discountFinalPrice;
	}

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    

}
