package com.zengshi.ecp.unpf.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UnpfOrdMain implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
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
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getParamid() {
        return paramid;
    }

    public void setParamid(String paramid) {
        this.paramid = paramid == null ? null : paramid.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg == null ? null : buyerMsg.trim();
    }

    public String getContractProvince() {
        return contractProvince;
    }

    public void setContractProvince(String contractProvince) {
        this.contractProvince = contractProvince == null ? null : contractProvince.trim();
    }

    public String getContractCity() {
        return contractCity;
    }

    public void setContractCity(String contractCity) {
        this.contractCity = contractCity == null ? null : contractCity.trim();
    }

    public String getContractDistrict() {
        return contractDistrict;
    }

    public void setContractDistrict(String contractDistrict) {
        this.contractDistrict = contractDistrict == null ? null : contractDistrict.trim();
    }

    public String getContractZipcode() {
        return contractZipcode;
    }

    public void setContractZipcode(String contractZipcode) {
        this.contractZipcode = contractZipcode == null ? null : contractZipcode.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getContractAddr() {
        return contractAddr;
    }

    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType == null ? null : dispatchType.trim();
    }

    public String getContractTel() {
        return contractTel;
    }

    public void setContractTel(String contractTel) {
        this.contractTel = contractTel == null ? null : contractTel.trim();
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum == null ? null : contractNum.trim();
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
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag == null ? null : appFlag.trim();
    }

    public String getEcpStaffCode() {
        return ecpStaffCode;
    }

    public void setEcpStaffCode(String ecpStaffCode) {
        this.ecpStaffCode = ecpStaffCode == null ? null : ecpStaffCode.trim();
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
        this.ecpScoreFlag = ecpScoreFlag == null ? null : ecpScoreFlag.trim();
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
        this.sysFlag = sysFlag == null ? null : sysFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", outerId=").append(outerId);
        sb.append(", platType=").append(platType);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", paramid=").append(paramid);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", realExpressFee=").append(realExpressFee);
        sb.append(", realMoney=").append(realMoney);
        sb.append(", status=").append(status);
        sb.append(", buyerMsg=").append(buyerMsg);
        sb.append(", contractProvince=").append(contractProvince);
        sb.append(", contractCity=").append(contractCity);
        sb.append(", contractDistrict=").append(contractDistrict);
        sb.append(", contractZipcode=").append(contractZipcode);
        sb.append(", contractName=").append(contractName);
        sb.append(", contractAddr=").append(contractAddr);
        sb.append(", dispatchType=").append(dispatchType);
        sb.append(", contractTel=").append(contractTel);
        sb.append(", contractNum=").append(contractNum);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", dispatchTime=").append(dispatchTime);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", expressCompany=").append(expressCompany);
        sb.append(", remark=").append(remark);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", closeReason=").append(closeReason);
        sb.append(", invoiceTitle=").append(invoiceTitle);
        sb.append(", appFlag=").append(appFlag);
        sb.append(", ecpStaffCode=").append(ecpStaffCode);
        sb.append(", ecpStaffId=").append(ecpStaffId);
        sb.append(", ecpScoreFlag=").append(ecpScoreFlag);
        sb.append(", ecpScore=").append(ecpScore);
        sb.append(", importTime=").append(importTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysFlag=").append(sysFlag);
        sb.append("]");
        return sb.toString();
    }
}