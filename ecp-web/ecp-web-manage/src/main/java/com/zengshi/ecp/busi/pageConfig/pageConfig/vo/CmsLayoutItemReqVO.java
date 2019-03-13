package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;

public class CmsLayoutItemReqVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4425004613899221973L;

    //-----------------------//
    List<CmsLayoutItemPreReqDTO> layoutItemList;
    
    List<CmsTemplateLayoutItemReqDTO> templateLayoutItemList;
    //----------------------//
    private Long id;

    private Long layoutId;
    
    private Long templateId;

    private Long pageId;

    private Long modularId;

    private String status;

    private String itemSize;

    private Integer itemNo;
    
    private Integer rowNo;
    
    private String componentVmUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Long layoutId) {
        this.layoutId = layoutId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getModularId() {
        return modularId;
    }

    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public List<CmsLayoutItemPreReqDTO> getLayoutItemList() {
        return layoutItemList;
    }

    public void setLayoutItemList(List<CmsLayoutItemPreReqDTO> layoutItemList) {
        this.layoutItemList = layoutItemList;
    }

    public List<CmsTemplateLayoutItemReqDTO> getTemplateLayoutItemList() {
        return templateLayoutItemList;
    }

    public void setTemplateLayoutItemList(List<CmsTemplateLayoutItemReqDTO> templateLayoutItemList) {
        this.templateLayoutItemList = templateLayoutItemList;
    }
    public String getComponentVmUrl() {
        return componentVmUrl;
    }

    public void setComponentVmUrl(String componentVmUrl) {
        this.componentVmUrl = componentVmUrl;
    }

    @Override
    public String toString() {
        return "CmsLayoutItemReqVO [id=" + id + ", layoutId=" + layoutId + ", pageId=" + pageId
                + ", modularId=" + modularId + ", status=" + status + ", itemSize=" + itemSize
                + ", itemNo=" + itemNo + "]";
    }
    
}