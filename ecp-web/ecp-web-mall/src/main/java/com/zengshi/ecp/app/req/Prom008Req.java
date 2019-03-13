package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Prom008Req extends IBody{
	
	private Long siteId;
	
	private int  pageSize;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
}
