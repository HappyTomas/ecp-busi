package com.zengshi.ecp.aip.third.dubbo.dto.req;

import java.util.HashMap;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class PropReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = 1L;
	 
	//分类代码
	private String outCatgCode;
	
	//属性id
	private String propId;
	
	//父属性id
	private String parentPropId;
	
	//扩展参数传值
	private HashMap expandMap;

	public String getOutCatgCode() {
		return outCatgCode;
	}

	public void setOutCatgCode(String outCatgCode) {
		this.outCatgCode = outCatgCode;
	}

	public HashMap getExpandMap() {
		return expandMap;
	}

	public void setExpandMap(HashMap expandMap) {
		this.expandMap = expandMap;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getParentPropId() {
		return parentPropId;
	}

	public void setParentPropId(String parentPropId) {
		this.parentPropId = parentPropId;
	}
 
}

