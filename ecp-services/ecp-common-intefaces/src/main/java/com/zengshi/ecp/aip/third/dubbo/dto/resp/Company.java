package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JSONField(name="id")
	private Long id;
	
	@JSONField(name="code")
	private String code;
	
	@JSONField(name="name")
	private String  name;
	
	@JSONField(name="reg_mail_no")
	private String regMailNo;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegMailNo() {
		return regMailNo;
	}
	public void setRegMailNo(String regMailNo) {
		this.regMailNo = regMailNo;
	}

}
