/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.buyer.vo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
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
       
   
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    private String nickname;
    
    
    private String gender;
    
   
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date custBirthday;
    
    @Email(message="{email.not.correct}")
    private String Email;
    
    private String custName;
    
    
    private String provinceCode;
    
    private String cityCode;
    
    private String countyCode;
    
    private String telephone;

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
    
    private String custPic;
    
    public String getCustPic() {
		return custPic;
	}

	public void setCustPic(String custPic) {
		this.custPic = custPic;
	}

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

