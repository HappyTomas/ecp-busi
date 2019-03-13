package com.zengshi.ecp.busi.pageConfig.pageInfo.vo;

import java.io.Serializable;

public class SkinPageConfigVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3705453879431068370L;
    
    private Long shopId;//店铺id
    
    private String mallskintomanage;//是否是卖家中心跳转过来的标识符
    
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMallskintomanage() {
        return mallskintomanage;
    }

    public void setMallskintomanage(String mallskintomanage) {
        this.mallskintomanage = mallskintomanage;
    }
    
    

}

