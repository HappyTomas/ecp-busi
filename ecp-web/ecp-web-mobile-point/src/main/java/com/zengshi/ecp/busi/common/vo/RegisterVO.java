package com.zengshi.ecp.busi.common.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.common.ICriteria;

public class RegisterVO extends BaseInfo<ICriteria> {
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

    private String custShopFlag;

    private Long shopId;

    private String datailedAddress;

    private Long companyId;

    private String disturbFlag;

    private String status;

    private String checkStatus;

    private Long checkStaff;

    private Timestamp checkDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String staffPassword;
    
    private String custCardId;
    
    private String phoneCode;
    
    private String captchaCode;

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

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

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
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
        this.custCardId = custCardId;
    }

    public Timestamp getCustBirthday() {
        return custBirthday;
    }

    public void setCustBirthday(Timestamp custBirthday) {
        this.custBirthday = custBirthday;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
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
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", custPic=").append(custPic);
        sb.append(", gender=").append(gender);
        sb.append(", email=").append(email);
        sb.append(", telephone=").append(telephone);
        sb.append(", custShopFlag=").append(custShopFlag);
        sb.append(", shopId=").append(shopId);
        sb.append(", datailedAddress=").append(datailedAddress);
        sb.append(", companyId=").append(companyId);
        sb.append(", disturbFlag=").append(disturbFlag);
        sb.append(", status=").append(status);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", checkStaff=").append(checkStaff);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", custBirthday=").append(custBirthday);
        sb.append(", staffPassword=").append(staffPassword);
        sb.append(", custCardId=").append(custCardId);
        sb.append("]");
        return sb.toString();
    }
}