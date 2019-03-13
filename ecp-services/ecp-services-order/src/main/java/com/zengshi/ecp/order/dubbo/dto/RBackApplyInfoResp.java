package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackApplyInfoResp extends BaseResponseDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4928788957150851140L;
	
	//主订单id
	private String orderId;
	
	//退货申请单id
	private Long backId;
	
    
	//退款金额
	private Long backMoney;
	
	//是否最后一条
	private boolean lastFlag;

	//分摊比例
	private long scale;
	
	//退货积分信息
	private RBackApplyScoreResp rBackApplyScoreResp;
	
	//退货账户信息
	private RBackApplyAccResp rBackApplyAccResp;
	
	//退货优惠劵信息
	private RBackApplyCoupResp rBackApplyCoupResp;

	public long getScale() {
		return scale;
	}

	public void setScale(long scale) {
		this.scale = scale;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getBackId() {
		return backId;
	}

	public void setBackId(Long backId) {
		this.backId = backId;
	}

	public RBackApplyScoreResp getrBackApplyScoreResp() {
		return rBackApplyScoreResp;
	}

	public void setrBackApplyScoreResp(RBackApplyScoreResp rBackApplyScoreResp) {
		this.rBackApplyScoreResp = rBackApplyScoreResp;
	}

	public RBackApplyAccResp getrBackApplyAccResp() {
		return rBackApplyAccResp;
	}

	public void setrBackApplyAccResp(RBackApplyAccResp rBackApplyAccResp) {
		this.rBackApplyAccResp = rBackApplyAccResp;
	}

	public RBackApplyCoupResp getrBackApplyCoupResp() {
		return rBackApplyCoupResp;
	}

	public void setrBackApplyCoupResp(RBackApplyCoupResp rBackApplyCoupResp) {
		this.rBackApplyCoupResp = rBackApplyCoupResp;
	}

	public Long getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Long backMoney) {
		this.backMoney = backMoney;
	}

	public boolean isLastFlag() {
		return lastFlag;
	}


	public void setLastFlag(boolean lastFlag) {
		this.lastFlag = lastFlag;
	}

    
}

