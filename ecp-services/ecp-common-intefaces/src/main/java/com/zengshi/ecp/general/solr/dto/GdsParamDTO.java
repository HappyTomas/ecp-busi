package com.zengshi.ecp.general.solr.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsParamDTO  extends BaseInfo{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private Long catalogId;
	

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
}

