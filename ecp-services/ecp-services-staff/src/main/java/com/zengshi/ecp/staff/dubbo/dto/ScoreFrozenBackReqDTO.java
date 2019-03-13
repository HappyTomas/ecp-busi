package com.zengshi.ecp.staff.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ScoreFrozenBackReqDTO extends BaseInfo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long staffId;

    private String orderId;

    private Long siteId;
    
    private Long backScore;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getBackScore() {
		return backScore;
	}

	public void setBackScore(Long backScore) {
		this.backScore = backScore;
	}

   
}