package com.zengshi.ecp.busi.unpf.auth.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ShopLimitVO extends EcpBasePageReqVO {

    private static final long serialVersionUID = 1L;
    private String platType;
    private String shopId;
    private String shopAuthId;
    public String getPlatType() {
        return platType;
    }
    public void setPlatType(String platType) {
        this.platType = platType;
    }
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getShopAuthId() {
        return shopAuthId;
    }
    public void setShopAuthId(String shopAuthId) {
        this.shopAuthId = shopAuthId;
    }
    
    
}

