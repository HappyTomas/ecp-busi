package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Prom007Req extends IBody{
	
	private Long promId;
	
    private int pageSize;
    
    private int pageNo;


	public Long getPromId() {
		return promId;
	}

	public void setPromId(Long promId) {
		this.promId = promId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
