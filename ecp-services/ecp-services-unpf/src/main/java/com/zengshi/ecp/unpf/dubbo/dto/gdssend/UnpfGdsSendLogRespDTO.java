package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月17日 下午2:51:40 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfGdsSendLogRespDTO extends GdsInfoRespDTO {
	
	private static final long serialVersionUID = 1L;
	
    private String platType;

    private String platTypeName;
    
    private String outCatgCode;

    private Long gdsId;

    private String action;

    private String status;

    private BigDecimal sendCnt=BigDecimal.ZERO;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Long shopAuthId;

    private String outGdsId;

    private String errors;

	public String getPlatType() {
		return platType;
	}


	public String getPlatTypeName() {
		return platTypeName;
	}


	public void setPlatTypeName(String platTypeName) {
		this.platTypeName = platTypeName;
	}


	public String getOutCatgCode() {
		return outCatgCode;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public String getAction() {
		return action;
	}

	public String getStatus() {
		return status;
	}

	public BigDecimal getSendCnt() {
		return sendCnt;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public String getOutGdsId() {
		return outGdsId;
	}

	public String getErrors() {
		return errors;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public void setOutCatgCode(String outCatgCode) {
		this.outCatgCode = outCatgCode;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSendCnt(BigDecimal sendCnt) {
		this.sendCnt = sendCnt;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public void setOutGdsId(String outGdsId) {
		this.outGdsId = outGdsId;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
}


