package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pay002Req extends IBody {

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * shopId:店铺ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
}

