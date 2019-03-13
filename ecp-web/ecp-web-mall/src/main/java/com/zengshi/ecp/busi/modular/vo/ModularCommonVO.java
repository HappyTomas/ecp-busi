package com.zengshi.ecp.busi.modular.vo;

import java.io.Serializable;

public class ModularCommonVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3091410488046477L;
    
    private Long pageId;//页面ID
    
    private Long shopId;//店铺ID
    
    private Long pageTypeId;//页面类型ID

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getShopId() {
        return shopId;
    }
    
    public Long getPageTypeId() {
        return pageTypeId;
    }

    public void setPageTypeId(Long pageTypeId) {
        this.pageTypeId = pageTypeId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    

}

