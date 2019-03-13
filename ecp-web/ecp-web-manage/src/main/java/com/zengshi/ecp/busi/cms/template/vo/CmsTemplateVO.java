package com.zengshi.ecp.busi.cms.template.vo;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.7
 */  

public class CmsTemplateVO  extends EcpBasePageReqVO implements Serializable{
	private Long id;

	@NotNull(message="{cms.template.siteId.null.error}")
    private Long siteId;

    @NotNull(message="{cms.template.templateName.null.error}")
    @Length(max=20, message="{cms.template.templateName.length.error}")
    private String templateName;

    @NotNull(message="{cms.template.templateClass.null.error}")
    private String templateClass;

    @Length(max=256, message="{cms.template.templateFileUrl.length.error}")
    private String templateFileUrl;

    private String status;

    private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateClass() {
		return templateClass;
	}

	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}

	public String getTemplateFileUrl() {
		return templateFileUrl;
	}

	public void setTemplateFileUrl(String templateFileUrl) {
		this.templateFileUrl = templateFileUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
