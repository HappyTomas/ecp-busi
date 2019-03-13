package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CustAuthstrReqDTO extends BaseInfo{
    private Long id;

    private Long staffId;

    private String staffCode;

    private String serialNumber;

    private String companyName;

    private String companyAdress;

    private String trade;

    private String companyType;

    private String employeeNum;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String companyEmail;

    private String status;

    private String linkPersonMsg;

    private String linkPhoneMsg;

    private String linkPsnCard;

    private String linkTelephone;

    private String companyWeixin;

    private String companyQq;

    private Long parentId;

    private String checkStatus;

    private String checkRemark;

    private Long checkStaff;

    private Timestamp checkDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress == null ? null : companyAdress.trim();
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLinkPersonMsg() {
        return linkPersonMsg;
    }

    public void setLinkPersonMsg(String linkPersonMsg) {
        this.linkPersonMsg = linkPersonMsg == null ? null : linkPersonMsg.trim();
    }

    public String getLinkPhoneMsg() {
        return linkPhoneMsg;
    }

    public void setLinkPhoneMsg(String linkPhoneMsg) {
        this.linkPhoneMsg = linkPhoneMsg == null ? null : linkPhoneMsg.trim();
    }

    public String getLinkPsnCard() {
        return linkPsnCard;
    }

    public void setLinkPsnCard(String linkPsnCard) {
        this.linkPsnCard = linkPsnCard == null ? null : linkPsnCard.trim();
    }

    public String getLinkTelephone() {
        return linkTelephone;
    }

    public void setLinkTelephone(String linkTelephone) {
        this.linkTelephone = linkTelephone == null ? null : linkTelephone.trim();
    }

    public String getCompanyWeixin() {
        return companyWeixin;
    }

    public void setCompanyWeixin(String companyWeixin) {
        this.companyWeixin = companyWeixin == null ? null : companyWeixin.trim();
    }

    public String getCompanyQq() {
        return companyQq;
    }

    public void setCompanyQq(String companyQq) {
        this.companyQq = companyQq == null ? null : companyQq.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
    }

    public Long getCheckStaff() {
        return checkStaff;
    }

    public void setCheckStaff(Long checkStaff) {
        this.checkStaff = checkStaff;
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyAdress=").append(companyAdress);
        sb.append(", trade=").append(trade);
        sb.append(", companyType=").append(companyType);
        sb.append(", employeeNum=").append(employeeNum);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", status=").append(status);
        sb.append(", linkPersonMsg=").append(linkPersonMsg);
        sb.append(", linkPhoneMsg=").append(linkPhoneMsg);
        sb.append(", linkPsnCard=").append(linkPsnCard);
        sb.append(", linkTelephone=").append(linkTelephone);
        sb.append(", companyWeixin=").append(companyWeixin);
        sb.append(", companyQq=").append(companyQq);
        sb.append(", parentId=").append(parentId);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", checkRemark=").append(checkRemark);
        sb.append(", checkStaff=").append(checkStaff);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}