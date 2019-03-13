/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoReq.java 
 * Package Name:com.zengshi.ecp.app.req 
 * Date:2016-2-22下午6:52:57 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月7日上午11:40:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupDetailResp {
    
	private Long id;
	
	private Long CoupId;
	 //优惠券编码(对外展示,使用)
    private String coupNo;
    //优惠券名称
    private String coupName;
	//优惠券面额
	private Integer coupValue;
	
	private Long staffId;
	//生效时间
    private Timestamp activeTime;
    //失效时间
    private Timestamp inactiveTime;
    //店铺ID
    private Long shopId;
    //店铺名称
    private String shopName;
    //展示优惠券使用条件
  	private String conditions;
  	//状态0:失效,1:有效, 2:冻结
  	private String status;
  	//记录优惠券是否使用 0:未使用 , 1:使用
  	private String ifUse;
  	//0:不展示  1:新(2天内领取) 2:快过期
  	private String coupStatus;
  	
  	
	public String getCoupNo() {
		return coupNo;
	}
	public void setCoupNo(String coupNo) {
		this.coupNo = coupNo;
	}
	public String getCoupName() {
		return coupName;
	}
	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}
	public Integer getCoupValue() {
		return coupValue;
	}
	public void setCoupValue(Integer coupValue) {
		this.coupValue = coupValue;
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
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public Timestamp getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}
	public Timestamp getInactiveTime() {
		return inactiveTime;
	}
	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIfUse() {
		return ifUse;
	}
	public void setIfUse(String ifUse) {
		this.ifUse = ifUse;
	}
	public String getCoupStatus() {
		return coupStatus;
	}
	public void setCoupStatus(String coupStatus) {
		this.coupStatus = coupStatus;
	}
  	
  	
}

