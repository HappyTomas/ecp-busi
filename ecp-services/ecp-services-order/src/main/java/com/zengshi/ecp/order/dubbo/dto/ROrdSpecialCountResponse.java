package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdSpecialCountResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1760066717294867213L;
    
    /**
     * 时间范围
     */
    private int monthLimit;
    
    /**
     * 取消订单数量
     */
    private Long cancleOrdCount;
    
    /**
     * 退款退货成功数量
     */
    private Long backOrdCount;



	public int getMonthLimit() {
		return monthLimit;
	}

	public void setMonthLimit(int monthLimit) {
		this.monthLimit = monthLimit;
	}

	public Long getCancleOrdCount() {
		return cancleOrdCount;
	}

	public void setCancleOrdCount(Long cancleOrdCount) {
		this.cancleOrdCount = cancleOrdCount;
	}

	public Long getBackOrdCount() {
		return backOrdCount;
	}

	public void setBackOrdCount(Long backOrdCount) {
		this.backOrdCount = backOrdCount;
	}

}

