package com.zengshi.ecp.staff.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 一书一码激活赠送积分请求参数<br>
 * Date:2016-3-19下午3:42:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ScoreISBNActiveReqDTO extends BaseInfo {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private String staffCode;
    
    private String isbnCode;
    
    private String bbCode;
    
    private String bookCode;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

	public String getBbCode() {
		return bbCode;
	}

	public void setBbCode(String bbCode) {
		this.bbCode = bbCode;
	}
    
    
}