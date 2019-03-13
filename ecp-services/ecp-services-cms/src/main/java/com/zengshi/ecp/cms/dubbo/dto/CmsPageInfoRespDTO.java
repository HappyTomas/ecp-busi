package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsPageInfoRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
	/**
     * 发布二维码地址
     */
	private String qrcodePicUrl;
	/**
     * 预览二维码地址
     */
    private String viewQrcodePicUrl;
	/**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 是否使用模板翻译
     */
    private String isUseTemplateZH;
    /**
     * 页面类型翻译
     */
    private String pageTypeZH;
    /**
     * 平台类型翻译
     */
    private String platformTypeZH;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 已发布页面布局LIST
     */
    List<CmsLayoutPubRespDTO> layoutPubRespDTOList = new ArrayList<CmsLayoutPubRespDTO>();
    /**
     * 已发布页面属性
     */
    CmsPageAttrPubRespDTO pageAttrPubRespDTO;

    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private Long siteId;

    private Long pageTypeId;

    private String pageName;

    private String siteUrl;

    private Long shopId;

    private Timestamp timingTime;

    private Timestamp validTime;

    private Timestamp invalidTime;

    private String isUseTemplate;

    private Long templateId;

    private String platformType;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;
    
    private String qrcodePic;

    private String viewQrcodePic;
    

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

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getTimingTime() {
        return timingTime;
    }

    public void setTimingTime(Timestamp timingTime) {
        this.timingTime = timingTime;
    }

    public Timestamp getValidTime() {
        return validTime;
    }

    public void setValidTime(Timestamp validTime) {
        this.validTime = validTime;
    }

    public Timestamp getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Timestamp invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getIsUseTemplate() {
        return isUseTemplate;
    }

    public void setIsUseTemplate(String isUseTemplate) {
        this.isUseTemplate = isUseTemplate == null ? null : isUseTemplate.trim();
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
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

    public String getQrcodePic() {
		return qrcodePic;
	}

	public void setQrcodePic(String qrcodePic) {
		this.qrcodePic = qrcodePic;
	}
	
	public String getViewQrcodePic() {
		return viewQrcodePic;
	}

	public void setViewQrcodePic(String viewQrcodePic) {
		this.viewQrcodePic = viewQrcodePic;
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
        sb.append(", pageName=").append(pageName);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", shopId=").append(shopId);
        sb.append(", timingTime=").append(timingTime);
        sb.append(", validTime=").append(validTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", isUseTemplate=").append(isUseTemplate);
        sb.append(", templateId=").append(templateId);
        sb.append(", platformType=").append(platformType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", qrcodePic=").append(qrcodePic);
        sb.append(", viewQrcodePic=").append(viewQrcodePic);
        sb.append("]");
        return sb.toString();
    }
    
    public String getQrcodePicUrl() {
		return qrcodePicUrl;
	}

	public void setQrcodePicUrl(String qrcodePicUrl) {
		this.qrcodePicUrl = qrcodePicUrl;
	}

	public String getViewQrcodePicUrl() {
		return viewQrcodePicUrl;
	}

	public void setViewQrcodePicUrl(String viewQrcodePicUrl) {
		this.viewQrcodePicUrl = viewQrcodePicUrl;
	}

	public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public String getPageTypeZH() {
        return pageTypeZH;
    }

    public void setPageTypeZH(String pageTypeZH) {
        this.pageTypeZH = pageTypeZH;
    }

    public String getPlatformTypeZH() {
        return platformTypeZH;
    }

    public void setPlatformTypeZH(String platformTypeZH) {
        this.platformTypeZH = platformTypeZH;
    }
    
    public String getIsUseTemplateZH() {
        return isUseTemplateZH;
    }

    public void setIsUseTemplateZH(String isUseTemplateZH) {
        this.isUseTemplateZH = isUseTemplateZH;
    }
    
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    public List<CmsLayoutPubRespDTO> getLayoutPubRespDTOList() {
        return layoutPubRespDTOList;
    }

    public void setLayoutPubRespDTOList(List<CmsLayoutPubRespDTO> layoutPubRespDTOList) {
        this.layoutPubRespDTOList = layoutPubRespDTOList;
    }

    public CmsPageAttrPubRespDTO getPageAttrPubRespDTO() {
        return pageAttrPubRespDTO;
    }

    public void setPageAttrPubRespDTO(CmsPageAttrPubRespDTO pageAttrPubRespDTO) {
        this.pageAttrPubRespDTO = pageAttrPubRespDTO;
    }
}
