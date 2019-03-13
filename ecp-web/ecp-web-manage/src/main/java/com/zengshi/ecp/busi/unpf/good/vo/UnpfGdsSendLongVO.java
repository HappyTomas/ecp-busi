package com.zengshi.ecp.busi.unpf.good.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月18日 上午11:13:07 
* @version 1.0 
**/
public class UnpfGdsSendLongVO extends EcpBasePageReqVO{

	private static final long serialVersionUID = -1L;

    private Long id;

    private Long shopAuthId;

    private Long sendId;

    private String platType;

    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private String action;

    private Timestamp createTime;

    private Long createStaff;

    private String outGdsId;

    private String errors;

    private String status;
    
	public Long getId() {
		return id;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public Long getSendId() {
		return sendId;
	}

	public String getPlatType() {
		return platType;
	}

	public Long getShopId() {
		return shopId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public String getAction() {
		return action;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public String getOutGdsId() {
		return outGdsId;
	}

	public String getErrors() {
		return errors;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public void setOutGdsId(String outGdsId) {
		this.outGdsId = outGdsId;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}


