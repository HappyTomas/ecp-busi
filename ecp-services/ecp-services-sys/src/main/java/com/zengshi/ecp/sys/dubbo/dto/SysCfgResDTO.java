package com.zengshi.ecp.sys.dubbo.dto;

import java.io.Serializable;
import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SysCfgResDTO extends BaseResponseDTO implements Serializable {

	private static final long serialVersionUID = -4316703319166271633L;
	private String paraCode;

    private String paraValue;
    
    private String paraDesc;

    private Date createTime;
    
    private Long createStaff;
    
    private Date updateTime;

    private Long updateStaff;

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

}
