package com.zengshi.ecp.busi.staff.buyer.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class AuthStaffPwdVO extends EcpBasePageReqVO implements Serializable {
    /** 
	 * serialVersionUID:(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private Long staffId;

    private String staffCode;

    private String staffPasswd;
    
    private String staffPasswdOld;

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
		this.staffCode = staffCode;
	}

	public String getStaffPasswd() {
		return staffPasswd;
	}

	public void setStaffPasswd(String staffPasswd) {
		this.staffPasswd = staffPasswd;
	}

	public String getStaffPasswdOld() {
		return staffPasswdOld;
	}

	public void setStaffPasswdOld(String staffPasswdOld) {
		this.staffPasswdOld = staffPasswdOld;
	}
    
}