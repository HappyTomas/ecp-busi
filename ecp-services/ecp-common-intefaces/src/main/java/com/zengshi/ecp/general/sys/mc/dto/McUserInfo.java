/** 
 * Project Name:ecp-common-intefaces 
 * File Name:McParamsDTO.java 
 * Package Name:com.zengshi.ecp.general.sys.mc.dto 
 * Date:2016年3月5日下午4:54:22 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.general.sys.mc.dto;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-common-intefaces <br>
 * Description: 调用消息中心所使用的基础参数<br>
 * Date:2016年3月5日下午4:54:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McUserInfo  implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1158603033163011029L;

    private long userId;
    
    private String phoneNo;
    
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "McUserInfo [userId=" + userId + ", phoneNo=" + phoneNo + ", email=" + email + "]";
    }
    
    

}

