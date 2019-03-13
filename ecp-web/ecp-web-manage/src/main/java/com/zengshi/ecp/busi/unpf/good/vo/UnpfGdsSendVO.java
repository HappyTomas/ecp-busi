package com.zengshi.ecp.busi.unpf.good.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月18日 上午11:13:07 
* @version 1.0 
**/
public class UnpfGdsSendVO extends EcpBasePageReqVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	private Long shopId;
	
	private String sendGds;
	
	private String platType;
	
	private Long shopAuthId;
	
	private Long gdsId;
	
	public Long getShopId() {
		return shopId;
	}

	public String getSendGds() {
		return sendGds;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setSendGds(String sendGds) {
		this.sendGds = sendGds;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}
}


