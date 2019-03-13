package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO;

public class CmsUpdateLayoutBatchReqVO extends EcpBasePageReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6969894997898539400L;
    
    /** 
     * layoutList:TODO(布局预览表). 
     * @since JDK 1.6 
     */ 
    List<CmsLayoutPreReqDTO> layoutList;
    /** 
     * templateLayoutReqDTO(模板布局表). 
     * @since JDK 1.6 
     */ 
    List<CmsTemplateLayoutReqDTO> templateLayoutList;

    public List<CmsTemplateLayoutReqDTO> getTemplateLayoutList() {
        return templateLayoutList;
    }

    public void setTemplateLayoutList(List<CmsTemplateLayoutReqDTO> templateLayoutList) {
        this.templateLayoutList = templateLayoutList;
    }

    public List<CmsLayoutPreReqDTO> getLayoutList() {
        return layoutList;
    }

    public void setLayoutList(List<CmsLayoutPreReqDTO> layoutList) {
        this.layoutList = layoutList;
    }
    
}