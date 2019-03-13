package com.zengshi.ecp.busi.goods.vo;

import org.hibernate.validator.constraints.Length;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsCatlogVO extends  EcpBasePageReqVO{
	
    private static final long serialVersionUID = -6620744971867241470L;
    
	private Long id;
	@Length(max=64, message="{名称长度不能超过64个字符}")
    private String catlogName;
	@Length(max=16, message="{编码长度不能超过16个字符}")
    private String catlogCode;
    
    private String operateId;
    @Length(max=100, message="{描述长度不能超过100个字符}")
    private String catlogDesc;
    
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatlogName() {
		return catlogName;
	}

	public void setCatlogName(String catlogName) {
		this.catlogName = catlogName;
	}

	public String getCatlogCode() {
		return catlogCode;
	}

	public void setCatlogCode(String catlogCode) {
		this.catlogCode = catlogCode;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getCatlogDesc() {
		return catlogDesc;
	}

	public void setCatlogDesc(String catlogDesc) {
		this.catlogDesc = catlogDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
    
}
