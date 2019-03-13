package com.zengshi.ecp.busi.pageConfig.utils.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CmsLinkInputUtilVO extends EcpBasePageReqVO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Long siteId;
    
    private Long pageTypeId;
    
    @NotNull(message="{cms.pageConfig.CmsLinkInputUtilVO.typeName.null.error}")
    private String typeName;
    
    private String flatType;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }
    
}