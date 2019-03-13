package com.zengshi.ecp.busi.seller.goods.vo;

import java.io.Serializable;

public class GdsShopVO implements Serializable{

    /** 
     * serialVersionUID:TODO(专门用来只传shopId的). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2536892771927041890L;
    /**
     * 店铺编码
     */
    private String shopId;
    
    private String gdsStatus;
    
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
	public String getGdsStatus() {
		return gdsStatus;
	}
	public void setGdsStatus(String gdsStatus) {
		this.gdsStatus = gdsStatus;
	}
    
    
    
}

