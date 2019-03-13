package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrdCartsReqVO extends EcpBasePageReqVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4003941059579463955L;
	
	Long                         staffId;
	List<ROrdCartReqVO>        ordCartList;
	
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public List<ROrdCartReqVO> getOrdCartList() {
		return ordCartList;
	}
	public void setOrdCartList(List<ROrdCartReqVO> ordCartList) {
		this.ordCartList = ordCartList;
	}
	
}
