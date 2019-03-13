package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Prom006Req extends IBody{
	
	private Long siteId;
	
    private int pageSize;
    
    private int pageNo;

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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
