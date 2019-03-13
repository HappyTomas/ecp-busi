package com.zengshi.ecp.busi.sys.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class AppVersionVO extends  EcpBasePageReqVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7123504112851141868L;


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

    private Timestamp updateTime;

    private Long createStaff;

    private Long updateStaff;

    private Long publishStaff;

    public String getVerDetailUrl() {
		return verDetailUrl;
	}

	public void setVerDetailUrl(String verDetailUrl) {
		this.verDetailUrl = verDetailUrl;
	}
//版本详情链接
	private String verDetailUrl;

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
        this.verProgram = verProgram == null ? null : verProgram.trim();
    }

    public String getVerPublishNo() {
        return verPublishNo;
    }

    public void setVerPublishNo(String verPublishNo) {
        this.verPublishNo = verPublishNo == null ? null : verPublishNo.trim();
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
        this.ifForce = ifForce == null ? null : ifForce.trim();
    }

    public String getVerDetail() {
        return verDetail;
    }

    public void setVerDetail(String verDetail) {
        this.verDetail = verDetail == null ? null : verDetail.trim();
    }

    public String getVerOs() {
        return verOs;
    }

    public void setVerOs(String verOs) {
        this.verOs = verOs == null ? null : verOs.trim();
    }

    public String getVerAdaptOs() {
        return verAdaptOs;
    }

    public void setVerAdaptOs(String verAdaptOs) {
        this.verAdaptOs = verAdaptOs == null ? null : verAdaptOs.trim();
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
        this.verUrl = verUrl == null ? null : verUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", verProgram=").append(verProgram);
        sb.append(", verPublishNo=").append(verPublishNo);
        sb.append(", verNo=").append(verNo);
        sb.append(", ifForce=").append(ifForce);
        sb.append(", verDetail=").append(verDetail);
        sb.append(", verOs=").append(verOs);
        sb.append(", verAdaptOs=").append(verAdaptOs);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", verUrl=").append(verUrl);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", publishStaff=").append(publishStaff);
        sb.append("]");
        return sb.toString();
    }

	
}
