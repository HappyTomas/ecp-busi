package com.zengshi.ecp.busi.cms.site.vo;

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

public class CmsSiteVO  extends EcpBasePageReqVO implements Serializable{
    //---------------------------------------------------//
    private Long id;

	@NotNull(message="{cms.site.siteName.null.error}")
    @Length(max=20, message="{cms.site.siteName.length.error}")
    private String siteName;

	@NotNull(message="{cms.site.siteUrl.null.error}")
    @Length(max=100, message="{cms.site.siteUrl.length.error}")
    private String siteUrl;

    private String status;
    
    private String isdefault;

	private Long createStaff;
	
    private String siteLogo;

	@NotNull(message="{cms.site.caseNo.null.error}")
    @Length(max=100, message="{cms.site.caseNo.length.error}")
    private String caseNo;

	@NotNull(message="{cms.site.siteAddress.null.error}")
    @Length(max=200, message="{cms.site.siteAddress.length.error}")
    private String siteAddress;

	@NotNull(message="{cms.site.copyrightCompany.null.error}")
    @Length(max=200, message="{cms.site.copyrightCompany.length.error}")
    private String copyrightCompany;

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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getCopyrightCompany() {
		return copyrightCompany;
	}

	public void setCopyrightCompany(String copyrightCompany) {
		this.copyrightCompany = copyrightCompany;
	}

	
}
