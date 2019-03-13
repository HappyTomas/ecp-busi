package com.zengshi.ecp.busi.seller.goods.vo;

import java.io.Serializable;

public class GdsInfoEditVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3355081918418198450L;
    
    private Long gdsId;//商品编码
    
    private String formCopyFlag;//是否是复制商品标识。1是，0或者空  否
    
    private String gdsVerifyFlag;//是否是商品审核

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getFormCopyFlag() {
        return formCopyFlag;
    }

    public void setFormCopyFlag(String formCopyFlag) {
        this.formCopyFlag = formCopyFlag;
    }

    public String getGdsVerifyFlag() {
        return gdsVerifyFlag;
    }

    public void setGdsVerifyFlag(String gdsVerifyFlag) {
        this.gdsVerifyFlag = gdsVerifyFlag;
    }
    
}

