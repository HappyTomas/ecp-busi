package com.zengshi.ecp.unpf.dubbo.dto.order;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RUnpfQueryOrderReq extends BaseInfo {

	private static final long serialVersionUID = 3357150779586507177L;

	private String id;

	private String outerId;

	private String platType;

	private Long shopId;

	private String shopName;

	private String paramid;

	private String orderCode;

	private String staffCode;

	private Long realExpressFee;

	private Long realMoney;

	private String status;

	private String buyerMsg;

	private String contractProvince;

	private String contractCity;

	private String contractDistrict;

	private String contractZipcode;

	private String contractName;

	private String contractAddr;

	private String dispatchType;

	private String contractTel;

	private String contractNum;

	private Timestamp orderTime;

	private Timestamp payTime;

	private Timestamp dispatchTime;

	private String expressNo;

	private String expressCompany;

	private String remark;

	private String orderAmount;

	private String closeReason;

	private String invoiceTitle;

	private String appFlag;

	private String ecpStaffCode;

	private Long ecpStaffId;

	private String ecpScoreFlag;

	private Long ecpScore;

	private Timestamp importTime;

	private Timestamp createTime;

	private Long createStaff;

	private Long updateStaff;

	private Timestamp updateTime;

	private String sysFlag;
	
	private Timestamp begDate;

	private Timestamp endDate;

	/**
	 * 导出文件关联KEY
	 */
	private Long exportKey;
	/**
	 * 导出类型
	 */
	private String exportType;

	public Long getExportKey() {
		return exportKey;
	}

	public void setExportKey(Long exportKey) {
		this.exportKey = exportKey;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
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

	public String getParamid() {
		return paramid;
	}

	public void setParamid(String paramid) {
		this.paramid = paramid;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Long getRealExpressFee() {
		return realExpressFee;
	}

	public void setRealExpressFee(Long realExpressFee) {
		this.realExpressFee = realExpressFee;
	}

	public Long getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Long realMoney) {
		this.realMoney = realMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyerMsg() {
		return buyerMsg;
	}

	public void setBuyerMsg(String buyerMsg) {
		this.buyerMsg = buyerMsg;
	}

	public String getContractProvince() {
		return contractProvince;
	}

	public void setContractProvince(String contractProvince) {
		this.contractProvince = contractProvince;
	}

	public String getContractCity() {
		return contractCity;
	}

	public void setContractCity(String contractCity) {
		this.contractCity = contractCity;
	}

	public String getContractDistrict() {
		return contractDistrict;
	}

	public void setContractDistrict(String contractDistrict) {
		this.contractDistrict = contractDistrict;
	}

	public String getContractZipcode() {
		return contractZipcode;
	}

	public void setContractZipcode(String contractZipcode) {
		this.contractZipcode = contractZipcode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractAddr() {
		return contractAddr;
	}

	public void setContractAddr(String contractAddr) {
		this.contractAddr = contractAddr;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Timestamp getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Timestamp dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getAppFlag() {
		return appFlag;
	}

	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}

	public String getEcpStaffCode() {
		return ecpStaffCode;
	}

	public void setEcpStaffCode(String ecpStaffCode) {
		this.ecpStaffCode = ecpStaffCode;
	}

	public Long getEcpStaffId() {
		return ecpStaffId;
	}

	public void setEcpStaffId(Long ecpStaffId) {
		this.ecpStaffId = ecpStaffId;
	}

	public String getEcpScoreFlag() {
		return ecpScoreFlag;
	}

	public void setEcpScoreFlag(String ecpScoreFlag) {
		this.ecpScoreFlag = ecpScoreFlag;
	}

	public Long getEcpScore() {
		return ecpScore;
	}

	public void setEcpScore(Long ecpScore) {
		this.ecpScore = ecpScore;
	}

	public Timestamp getImportTime() {
		return importTime;
	}

	public void setImportTime(Timestamp importTime) {
		this.importTime = importTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public Timestamp getBegDate() {
		return begDate;
	}

	public void setBegDate(Timestamp begDate) {
		this.begDate = begDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	
}
