package com.zengshi.ecp.goods.dubbo.dto.excelImp;

import java.io.Serializable;

public class GdsExcelImpPropValueInfo implements Serializable{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -1249057141498012218L;
    private Long propId;

    private String propName;

    private Long propValueId;

    private String propValue;

    private String propType;

    private String propValueType;
    
    private String propInputType;

    private String propInputRule;
    
    private Long shopId;

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Long getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Long propValueId) {
		this.propValueId = propValueId;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropValueType() {
		return propValueType;
	}

	public void setPropValueType(String propValueType) {
		this.propValueType = propValueType;
	}

	public String getPropInputType() {
		return propInputType;
	}

	public void setPropInputType(String propInputType) {
		this.propInputType = propInputType;
	}

	public String getPropInputRule() {
		return propInputRule;
	}

	public void setPropInputRule(String propInputRule) {
		this.propInputRule = propInputRule;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}

