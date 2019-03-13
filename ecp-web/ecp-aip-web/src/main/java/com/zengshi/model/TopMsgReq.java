package com.zengshi.model;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * @author huangjx
 *
 */
public class TopMsgReq  extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
    private Long sysLogId;//log id
    
    private Long shopId;//店铺id
    
    private String platType;//平台类型
    
    private Long shopAuthId;//授权id

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public Long getSysLogId() {
		return sysLogId;
	}

	public void setSysLogId(Long sysLogId) {
		this.sysLogId = sysLogId;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}
	
}
