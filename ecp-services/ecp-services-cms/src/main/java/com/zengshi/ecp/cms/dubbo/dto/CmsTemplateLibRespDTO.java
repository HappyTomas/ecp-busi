
package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsTemplateLibRespDTO extends BaseResponseDTO {
    
    /*----以下是model新添加字段 start---*/
	/**
	 * 站点名称
	 */
	private String siteName;
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 平台类型翻译
     */
    private String platformTypeZH;
    /**
     * 模板类型翻译
     */
    private String templateTypeZH;
    /**
     * 是否默认模板翻译
     */
    private String isDefTemplateZH;
    /**
     * 页面类型翻译
     */
    private String pageTypeZH;

    /*----以下是model新添加字段  end ---*/

    private Long id;
    
    private Long siteId;

    private Long pageTypeId;

    private String templateName;
    
    private String platformType;

    private String templateType;

    private String status;

    private String showPic;

    private Long shopId;

    private String isDefTemplate;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

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

	public Long getPageTypeId() {
        return pageTypeId;
    }

    public void setPageTypeId(Long pageTypeId) {
        this.pageTypeId = pageTypeId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
	}

	public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIsDefTemplate() {
        return isDefTemplate;
    }

    public void setIsDefTemplate(String isDefTemplate) {
        this.isDefTemplate = isDefTemplate == null ? null : isDefTemplate.trim();
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

    public String getPlatformTypeZH() {
		return platformTypeZH;
	}

	public void setPlatformTypeZH(String platformTypeZH) {
		this.platformTypeZH = platformTypeZH;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteId=").append(siteId);
        sb.append(", pageTypeId=").append(pageTypeId);
        sb.append(", templateName=").append(templateName);
        sb.append(", platformType=").append(platformType);
        sb.append(", templateType=").append(templateType);
        sb.append(", status=").append(status);
        sb.append(", showPic=").append(showPic);
        sb.append(", shopId=").append(shopId);
        sb.append(", isDefTemplate=").append(isDefTemplate);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public String getTemplateTypeZH() {
        return templateTypeZH;
    }

    public void setTemplateTypeZH(String templateTypeZH) {
        this.templateTypeZH = templateTypeZH;
    }
    
    public String getIsDefTemplateZH() {
        return isDefTemplateZH;
    }

    public void setIsDefTemplateZH(String isDefTemplateZH) {
        this.isDefTemplateZH = isDefTemplateZH;
    }
    
    public String getPageTypeZH() {
        return pageTypeZH;
    }

    public void setPageTypeZH(String pageTypeZH) {
        this.pageTypeZH = pageTypeZH;
    }
    
}
