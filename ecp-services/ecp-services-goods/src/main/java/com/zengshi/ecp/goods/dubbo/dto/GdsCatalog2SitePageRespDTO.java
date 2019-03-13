package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsCatalog2SitePageRespDTO extends BaseResponseDTO {
	
    private static final long serialVersionUID = -1L;
    
    //状态
    private String statusZH;
    /**
     * 是否默认站点
     */
    private String isdefaultZH;

    private Long id;

    private String siteName;

    private String siteUrl;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String isdefault;
    
    private String siteLogo;

    private String caseNo;

    private String siteAddress;

    private String copyrightCompany;

    /**
     * 已经关联目录名称(逗号分隔)
     */
    private String catlogNames;
    /**
     * 已经关联目录ID(逗号分隔)
     */
    private String catlogIds;
    
    
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
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
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
	public String getCatlogNames() {
		return catlogNames;
	}
	public void setCatlogNames(String catlogNames) {
		this.catlogNames = catlogNames;
	}
	public String getCatlogIds() {
		return catlogIds;
	}
	public void setCatlogIds(String catlogIds) {
		this.catlogIds = catlogIds;
	}
	public String getStatusZH() {
		return statusZH;
	}
	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
	}
	public String getIsdefaultZH() {
		return isdefaultZH;
	}
	public void setIsdefaultZH(String isdefaultZH) {
		this.isdefaultZH = isdefaultZH;
	}
    
}
