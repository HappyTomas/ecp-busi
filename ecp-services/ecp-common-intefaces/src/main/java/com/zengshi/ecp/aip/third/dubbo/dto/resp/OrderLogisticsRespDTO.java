package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.fastjson.annotation.JSONField;

public class OrderLogisticsRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private List<Company> companies;

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
}
