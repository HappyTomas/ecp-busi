package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

public class RBackApplyLastResp extends BaseResponseDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4928788957150851140L;
	
	//是否最后一笔退货标示
	private boolean lastFlag;
	
	//退货退款总金额
	private Long backMoneys;

	public boolean isLastFlag() {
		return lastFlag;
	}

	public void setLastFlag(boolean lastFlag) {
		this.lastFlag = lastFlag;
	}

	public Long getBackMoneys() {
		return backMoneys;
	}

	public void setBackMoneys(Long backMoneys) {
		this.backMoneys = backMoneys;
	}
	
	


	

    
}

