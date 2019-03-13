package com.zengshi.ecp.busi.goods.gdsExcelImport.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ListResultReqVO extends EcpBasePageReqVO{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -6150251623711245512L;

	private String status;
	
	private Long importId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}
	
}

