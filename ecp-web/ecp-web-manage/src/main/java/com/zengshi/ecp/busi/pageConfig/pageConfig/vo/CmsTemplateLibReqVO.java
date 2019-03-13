package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CmsTemplateLibReqVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7705490600322981508L;
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 页面信息ID
     */
    private Long pageInfoId;
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;
    
    private Long siteId;

    @NotNull(message="{cms.templateLib.pageTypeId.null.error}")
    private Long pageTypeId;

    @NotNull(message="{cms.templateLib.templateName.null.error}")
    private String templateName;
    
    private String platformType;

    private String templateType;

    private String status;

    private String showPic;

    private Long shopId;

    private String isDefTemplate;

    public Long getPageInfoId() {
        return pageInfoId;
    }

    public void setPageInfoId(Long pageInfoId) {
        this.pageInfoId = pageInfoId;
    }

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
        this.templateName = templateName;
    }

    public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
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
        this.isDefTemplate = isDefTemplate;
    }

    @Override
    public String toString() {
        return "CmsTemplateLibReqVO [pageInfoId=" + pageInfoId + ", id=" + id + ", siteId=" + siteId + ", pageTypeId="
                + pageTypeId + ", templateName=" + templateName +", platformType=" + platformType
                + ", templateType=" + templateType
                + ", status=" + status + ", showPic=" + showPic + ", shopId=" + shopId
                + ", isDefTemplate=" + isDefTemplate + "]";
    }    
    
}