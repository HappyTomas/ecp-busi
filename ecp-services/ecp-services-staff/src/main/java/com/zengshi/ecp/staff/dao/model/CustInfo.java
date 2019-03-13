package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustInfo implements Serializable {
    private Long id;

    private String staffCode;

    private String custType;

    private Long custGrowValue;

    private String custLevelCode;

    private String nickname;

    private String custName;

    private Timestamp custBirthday;

    private String serialNumber;

    private String custPic;

    private String gender;

    private String email;

    private String telephone;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String custShopFlag;

    private Long shopId;

    private String datailedAddress;

    private Long companyId;

    private String disturbFlag;

    private String status;

    private Long checkStaff;

    private Timestamp checkDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String custCardId;

    private String orgId;

    private String orgName;

    private String sysType;

    private String sensitiveType;

    private String sensitiveDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public Long getCustGrowValue() {
        return custGrowValue;
    }

    public void setCustGrowValue(Long custGrowValue) {
        this.custGrowValue = custGrowValue;
    }

    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode == null ? null : custLevelCode.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public Timestamp getCustBirthday() {
        return custBirthday;
    }

    public void setCustBirthday(Timestamp custBirthday) {
        this.custBirthday = custBirthday;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic == null ? null : custPic.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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

    public String getCustShopFlag() {
        return custShopFlag;
    }

    public void setCustShopFlag(String custShopFlag) {
        this.custShopFlag = custShopFlag == null ? null : custShopFlag.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDatailedAddress() {
        return datailedAddress;
    }

    public void setDatailedAddress(String datailedAddress) {
        this.datailedAddress = datailedAddress == null ? null : datailedAddress.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDisturbFlag() {
        return disturbFlag;
    }

    public void setDisturbFlag(String disturbFlag) {
        this.disturbFlag = disturbFlag == null ? null : disturbFlag.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getCustCardId() {
        return custCardId;
    }

    public void setCustCardId(String custCardId) {
        this.custCardId = custCardId == null ? null : custCardId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }

    public String getSensitiveType() {
        return sensitiveType;
    }

    public void setSensitiveType(String sensitiveType) {
        this.sensitiveType = sensitiveType == null ? null : sensitiveType.trim();
    }

    public String getSensitiveDesc() {
        return sensitiveDesc;
    }

    public void setSensitiveDesc(String sensitiveDesc) {
        this.sensitiveDesc = sensitiveDesc == null ? null : sensitiveDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", custType=").append(custType);
        sb.append(", custGrowValue=").append(custGrowValue);
        sb.append(", custLevelCode=").append(custLevelCode);
        sb.append(", nickname=").append(nickname);
        sb.append(", custName=").append(custName);
        sb.append(", custBirthday=").append(custBirthday);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", custPic=").append(custPic);
        sb.append(", gender=").append(gender);
        sb.append(", email=").append(email);
        sb.append(", telephone=").append(telephone);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", custShopFlag=").append(custShopFlag);
        sb.append(", shopId=").append(shopId);
        sb.append(", datailedAddress=").append(datailedAddress);
        sb.append(", companyId=").append(companyId);
        sb.append(", disturbFlag=").append(disturbFlag);
        sb.append(", status=").append(status);
        sb.append(", checkStaff=").append(checkStaff);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", custCardId=").append(custCardId);
        sb.append(", orgId=").append(orgId);
        sb.append(", orgName=").append(orgName);
        sb.append(", sysType=").append(sysType);
        sb.append(", sensitiveType=").append(sensitiveType);
        sb.append(", sensitiveDesc=").append(sensitiveDesc);
        sb.append("]");
        return sb.toString();
    }
}