/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;


import org.hibernate.validator.constraints.Length;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description: 会员列表查询条件VO<br>
 * Date:2015-8-22上午11:49:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CustInfoListVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = -839813892423805647L;

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


    @Length(min=3, max=20, message="{staff.custCheck.staffCode.length.error}")
    private String staffCode;
    
    @Length(min=3, max=16, message="{staff.custCheck.serialNumber.length.error}")
    private String serialNumber;
    
    private String companyId;
    
    private String custLevelCode;
    
    private String sensitiveType;
    
    public String getSensitiveType() {
		return sensitiveType;
	}

	public void setSensitiveType(String sensitiveType) {
		this.sensitiveType = sensitiveType;
	}

	public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

	public String getCustLevelCode() {
		return custLevelCode;
	}

	public void setCustLevelCode(String custLevelCode) {
		this.custLevelCode = custLevelCode;
	}


}

