package com.zengshi.ecp.sys.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class AppVersionReqDTO extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7494898409533126081L;

	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVerProgram() {
		return verProgram;
	}

	public void setVerProgram(String verProgram) {
		this.verProgram = verProgram;
	}

	public String getVerPublishNo() {
		return verPublishNo;
	}

	public void setVerPublishNo(String verPublishNo) {
		this.verPublishNo = verPublishNo;
	}

	public Long getVerNo() {
		return verNo;
	}

	public void setVerNo(Long verNo) {
		this.verNo = verNo;
	}

	public String getIfForce() {
		return ifForce;
	}

	public void setIfForce(String ifForce) {
		this.ifForce = ifForce;
	}

	public String getVerDetail() {
		return verDetail;
	}

	public void setVerDetail(String verDetail) {
		this.verDetail = verDetail;
	}

	public String getVerOs() {
		return verOs;
	}

	public void setVerOs(String verOs) {
		this.verOs = verOs;
	}

	public String getVerAdaptOs() {
		return verAdaptOs;
	}

	public void setVerAdaptOs(String verAdaptOs) {
		this.verAdaptOs = verAdaptOs;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public String getVerUrl() {
		return verUrl;
	}

	public void setVerUrl(String verUrl) {
		this.verUrl = verUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUodateTime() {
		return uodateTime;
	}

	public void setUodateTime(Timestamp uodateTime) {
		this.uodateTime = uodateTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Long getPublishStaff() {
		return publishStaff;
	}

	public void setPublishStaff(Long publishStaff) {
		this.publishStaff = publishStaff;
	}

	private Long id;

	    private String verProgram;

	    private String verPublishNo;

	    private Long verNo;

	    private String ifForce;

	    private String verDetail;

	    private String verOs;

	    private String verAdaptOs;

	    private Timestamp publishTime;

	    private String verUrl;

	    private String status;

	    private Timestamp createTime;

	    private Timestamp uodateTime;

	    private Long createStaff;

	    private Long updateStaff;

	    private Long publishStaff;
	

}
