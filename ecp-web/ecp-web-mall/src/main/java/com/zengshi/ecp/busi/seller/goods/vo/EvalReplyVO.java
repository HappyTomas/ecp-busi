package com.zengshi.ecp.busi.seller.goods.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class EvalReplyVO extends EcpBasePageReqVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416063460506815189L;

	private Long evalId;

	private Long replyId;

	private String content;

	private Long gdsId;

	private String orderId;

	private String orderSubId;

	private Long staffId;

	private String detail;

	private Long shopId;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSubId() {
		return orderSubId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

}
