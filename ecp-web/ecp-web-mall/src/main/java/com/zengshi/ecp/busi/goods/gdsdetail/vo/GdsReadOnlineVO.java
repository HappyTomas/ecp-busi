package com.zengshi.ecp.busi.goods.gdsdetail.vo;

import java.io.Serializable;

public class GdsReadOnlineVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -111724442186276943L;
    /**
     * 商品编码
     */
    private Long gdsId;
    /**
     * 分类编码
     */
    private String catgCode;
    
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public String getCatgCode() {
        return catgCode;
    }
    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }
    
}

