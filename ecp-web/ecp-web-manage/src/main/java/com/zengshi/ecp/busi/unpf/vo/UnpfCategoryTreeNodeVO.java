package com.zengshi.ecp.busi.unpf.vo;

import com.zengshi.ecp.busi.goods.common.vo.BaseTreeVO;

public class UnpfCategoryTreeNodeVO extends BaseTreeVO{

    private static final long serialVersionUID = 1L;
    private String shopId;
    private String shopAuthId;
    private String srcSystem;
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getSrcSystem() {
        return srcSystem;
    }
    public void setSrcSystem(String srcSystem) {
        this.srcSystem = srcSystem;
    }
    public String getShopAuthId() {
        return shopAuthId;
    }
    public void setShopAuthId(String shopAuthId) {
        this.shopAuthId = shopAuthId;
    }
    
}

