package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CmsLayoutReqVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1218936654824704849L;

    /*----以下是model新添加字段  end ---*/
    private Long id;

    @NotNull(message="{.layout.pageId.null.error}")
    private Long pageId;
    
    private Long layoutTypeId;

    private String status;

    private String layoutShowType;
    
    private Integer showOrder;
    
    private String layoutItemSize;
    
    @NotNull(message="{cms.layout.pageTypeId.null.error}")
    private Long pageTypeId;
    
    private Long templateId;
    
    private String platformType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getLayoutTypeId() {
        return layoutTypeId;
    }

    public void setLayoutTypeId(Long layoutTypeId) {
        this.layoutTypeId = layoutTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public Long getPageTypeId() {
        return pageTypeId;
    }

    public void setPageTypeId(Long pageTypeId) {
        this.pageTypeId = pageTypeId;
    }

    public String getLayoutItemSize() {
        return layoutItemSize;
    }

    public void setLayoutItemSize(String layoutItemSize) {
        this.layoutItemSize = layoutItemSize;
    }

    public String getLayoutShowType() {
        return layoutShowType;
    }

    public void setLayoutShowType(String layoutShowType) {
        this.layoutShowType = layoutShowType;
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
		this.platformType = platformType;
	}

	@Override
    public String toString() {
        return "CmsLayoutReqVO [id=" + id + ", pageId=" + pageId + ", layoutTypeId=" + layoutTypeId
                + ", status=" + status + ", showOrder=" + showOrder + ", pageTypeId=" + pageTypeId
                + ", templateId=" + templateId +", platformType="+ platformType + "]";
    }
    
}