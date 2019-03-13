package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RQueryGoodPayedRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7669939625905251869L;
    
    /** 
     * skuId:单品ID. 
     * @since JDK 1.6 
     */ 
    private Long skuId;
    
    /** 
     * siteId:站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    
    

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    

}

