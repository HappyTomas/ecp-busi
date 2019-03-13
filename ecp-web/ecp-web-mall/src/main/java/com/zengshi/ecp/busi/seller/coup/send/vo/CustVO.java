package com.zengshi.ecp.busi.seller.coup.send.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-1-19 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class CustVO extends BaseResponseDTO{
    
    private Long id;

    private String staffCode;

    private String custType;
    
    private String custCardId;

    private Long custGrowValue;

    private String custLevelCode;
    
    private String custLevelName;

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
    
    private String shopName;

    private String datailedAddress;

    private Long companyId;
    
    private String companyName;
    
    private String disturbFlag;

    private String status;
    
    private String statusDesc; //鐘舵�杞箟鎻忚堪

    private String checkStatus;

    private Long checkStaff;

    private Timestamp checkDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    //鐪佷唤,鍦板競,鍖哄幙   鎷兼帴鐨勫瓧绗︿覆锛屼互閫楀彿鍒嗛殧
    private String pccString;
    
    private String roleIds; //鍏宠仈鐨勮鑹瞚d
    
    private String roleNames; //涓�roleIds瀵归綈锛屽叧鑱旂殑瑙掕壊鍚嶇О

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

    public String getCustLevelName() {
        return custLevelName;
    }

    public void setCustLevelName(String custLevelName) {
        this.custLevelName = custLevelName;
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
    
    public Timestamp getCustBirthday() {
        return custBirthday;
    }

    public void setCustBirthday(Timestamp custBirthday) {
        this.custBirthday = custBirthday;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
 

    public String getPccString() {
        return pccString;
    }

    public void setPccString(String pccString) {
        this.pccString = pccString;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getCustCardId() {
        return custCardId;
    }

    public void setCustCardId(String custCardId) {
        this.custCardId = custCardId;
    }
    
    


}
