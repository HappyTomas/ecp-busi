package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdFileImportDTO implements Serializable{
	private Long id;

	private String fromId;

	private Long shopId;

	private String orderId;

	private String fileName;

	private String status;

	private Timestamp importTime;

	private Long importStaff;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getImportTime() {
		return importTime;
	}

	public void setImportTime(Timestamp importTime) {
		this.importTime = importTime;
	}

	public Long getImportStaff() {
		return importStaff;
	}

	public void setImportStaff(Long importStaff) {
		this.importStaff = importStaff;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
