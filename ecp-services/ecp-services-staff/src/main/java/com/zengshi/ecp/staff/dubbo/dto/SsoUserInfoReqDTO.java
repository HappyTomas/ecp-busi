package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class SsoUserInfoReqDTO  implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getOrgUserType() {
        return OrgUserType;
    }

    public void setOrgUserType(String orgUserType) {
        OrgUserType = orgUserType;
    }

    private String UserName;
    
    private String Email;
    
    private String RealName;
    
    private String Mobile;
    
    private String OrgID;
    
    private String OrgName;
    
    private String OrgUserType;
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UserName=").append(UserName);
        sb.append(", Email=").append(Email);
        sb.append(", RealName=").append(RealName);
        sb.append(", Mobile=").append(Mobile);
        sb.append(", OrgID=").append(OrgID);
        sb.append(", OrgName=").append(OrgName);
        sb.append(", OrgUserType=").append(OrgUserType);
        sb.append("]");
        return sb.toString();
    }

}

