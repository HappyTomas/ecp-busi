package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ReportItemVO extends EcpBasePageReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362224043002381927L;
	// 店铺编码
	private Long shopId;
	// 商品审核状态
	private String verifyStatus;
	//评价审核状态
	private String auditStatus;
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
