package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CmsModularVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4146644153120723242L;

    private Long id;

    @NotNull(message="{cms.modular.modularName.null.error}")
    private String modularName;
    
    @NotNull(message="{cms.modular.modularType.null.error}")
    private String modularType;
    
    @NotNull(message="{cms.modular.platformType.null.error}")
    private String platformType;

    private String applyPageType;

    private String applyItemSize;

    private String showPic;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModularName() {
        return modularName;
    }

    public void setModularName(String modularName) {
        this.modularName = modularName;
    }

    public String getModularType() {
        return modularType;
    }

    public void setModularType(String modularType) {
        this.modularType = modularType;
    }

    public String getApplyPageType() {
        return applyPageType;
    }

    public void setApplyPageType(String applyPageType) {
        this.applyPageType = applyPageType;
    }

    public String getApplyItemSize() {
        return applyItemSize;
    }

    public void setApplyItemSize(String applyItemSize) {
        this.applyItemSize = applyItemSize;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CmsModularVO [id=" + id + ", modularName=" + modularName + ", modularType="
                + modularType + ", applyPageType=" + applyPageType + ", applyItemSize="
                + applyItemSize + ", showPic=" + showPic + ", status=" + status + "]";
    }
    
    
}