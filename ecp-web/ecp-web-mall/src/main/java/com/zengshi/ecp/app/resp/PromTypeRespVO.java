package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class PromTypeRespVO extends IBody{
	
	/** 
     * promTypeCode:促销类型编码. 
     */ 
    private String promTypeCode;
    
    /** 
     * promTypeName:促销类型名称. 
     */ 
    private String promTypeName;
    /** 
     * nameShort:简称. 
     */ 
    private String nameShort;
	public String getPromTypeCode() {
		return promTypeCode;
	}
	public void setPromTypeCode(String promTypeCode) {
		this.promTypeCode = promTypeCode;
	}
	public String getPromTypeName() {
		return promTypeName;
	}
	public void setPromTypeName(String promTypeName) {
		this.promTypeName = promTypeName;
	}
	public String getNameShort() {
		return nameShort;
	}
	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}
    
}
