package com.zengshi.ecp.busi.order.vo;

import java.util.List;

public class RPreOrdMainsResponseVO {
	private Long staffId;
	private List<RPreOrdMainResponseVO > preOrdMainList;
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public List<RPreOrdMainResponseVO> getPreOrdMainList() {
		return preOrdMainList;
	}
	public void setPreOrdMainList(List<RPreOrdMainResponseVO> preOrdMainList) {
		this.preOrdMainList = preOrdMainList;
	}
	
}
