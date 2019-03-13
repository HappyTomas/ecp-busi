/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.seller.coup.staff.vo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-5下午3:02:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7 
 */
public class CustInfoVO implements Serializable{
    
    /** 
     * serialVersionUID:
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = -1808106181284676714L;
    
   
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStaffPasswd() {
        return staffPasswd;
    }

    public void setStaffPasswd(String staffPasswd) {
        this.staffPasswd = staffPasswd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Date getCustBirthday() {
        return custBirthday;
    }

    public void setCustBirthday(Date custBirthday) {
        this.custBirthday = custBirthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDatailedAddress() {
        return datailedAddress;
    }

    public void setDatailedAddress(String datailedAddress) {
        this.datailedAddress = datailedAddress;
    }


    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode;
    }

   
    @Length(min=3, max=20, message="{staff.staffCode.length.error}")
    private String staffCode;
    
  
  /*  @Length(min=11, max=16, message="{staff.serialNumber.length.error}")*/
    private String serialNumber;
    
    
    
    @Length(min=6, max=20, message="{staff.staffPasswd.length.error}")
    private String staffPasswd;

   
    private String nickName;
    
    
    private String gender;
    
   
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date custBirthday;
    
    @Email
    private String Email;
    
    
   /* @Length(min=2, max=20, message="{staff.custName.length.error}")*/
    private String custName;
    
    
    private String provinceCode;
    
    private String cityCode;
    
    private String countyCode;
    
    private String telephone;
    @Length(min=0, max=64, message="{staff.datailedAddress.length.error}")
    private String datailedAddress;
    
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    private Long companyId;
    
    private Long shopId;
    
    private String custLevelCode;
    
    public String getDisturbFlag() {
        return disturbFlag;
    }

    public void setDisturbFlag(String disturbFlag) {
        this.disturbFlag = disturbFlag;
    }

    private String disturbFlag;
    
    private String staffId;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    

}

