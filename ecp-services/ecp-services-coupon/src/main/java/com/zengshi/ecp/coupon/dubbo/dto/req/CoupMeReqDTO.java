package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupMeReqDTO extends BaseInfo{
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long siteId;
	
	private Long CoupId;
	
	private Long staffId;
	//1:可使用 2:已使用 0:已过期
	private String opeType;
	/**
	 * 	USE_TIME //使用时间
	 *	CREATE_TIME //领取时间
	 *	COUP_VALUE //面额 
	 *  0:降序 1:升序
	 */
	private Map<String,String> mapSort;
	//app端口使用
	private String appPort;
	
	
	public String getAppPort() {
		return appPort;
	}

	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoupId() {
		return CoupId;
	}

	public void setCoupId(Long coupId) {
		CoupId = coupId;
	}

	public Map<String, String> getMapSort() {
		return mapSort;
	}

	public void setMapSort(Map<String, String> mapSort) {
		this.mapSort = mapSort;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getOpeType() {
		return opeType;
	}

	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}
	
	
}

