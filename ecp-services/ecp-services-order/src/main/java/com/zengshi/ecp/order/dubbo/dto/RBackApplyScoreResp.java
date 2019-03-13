package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackApplyScoreResp extends BaseResponseDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4928788957150851140L;
	
	
	
	//用户剩余可用积分
	private Long staffScore;
	
	//返还总积分
	private Long sumUsedCulateScore;
	
	//扣减总积分
	private Long sumReduCulateScore;
	
    

	public Long getSumUsedCulateScore() {
		return sumUsedCulateScore;
	}

	public void setSumUsedCulateScore(Long sumUsedCulateScore) {
		this.sumUsedCulateScore = sumUsedCulateScore;
	}

	public Long getSumReduCulateScore() {
		return sumReduCulateScore;
	}

	public void setSumReduCulateScore(Long sumReduCulateScore) {
		this.sumReduCulateScore = sumReduCulateScore;
	}


	public Long getStaffScore() {
		return staffScore;
	}

	public void setStaffScore(Long staffScore) {
		this.staffScore = staffScore;
	}
	

    
}

