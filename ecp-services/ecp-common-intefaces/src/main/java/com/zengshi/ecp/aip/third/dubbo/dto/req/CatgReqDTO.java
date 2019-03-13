package com.zengshi.ecp.aip.third.dubbo.dto.req;

import java.util.HashMap;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class CatgReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = 1L;
	 
	//分类代码
	private String outCatgCode;
	
	//品牌id
	private String brandId;
	
	//父分类代码
	private String outParentCatgCode;
	
	//淘宝 匹配规则xml值
	private String matchXml;
	
	//扩展参数传值
	private HashMap expandMap;

	public String getOutCatgCode() {
		return outCatgCode;
	}

	public void setOutCatgCode(String outCatgCode) {
		this.outCatgCode = outCatgCode;
	}

	public String getOutParentCatgCode() {
		return outParentCatgCode;
	}

	public void setOutParentCatgCode(String outParentCatgCode) {
		this.outParentCatgCode = outParentCatgCode;
	}

	public HashMap getExpandMap() {
		return expandMap;
	}

	public void setExpandMap(HashMap expandMap) {
		this.expandMap = expandMap;
	}

	public String getMatchXml() {
		return matchXml;
	}

	public void setMatchXml(String matchXml) {
		this.matchXml = matchXml;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
 
}

