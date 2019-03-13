package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class REntityCodeChgRequest extends BaseInfo {

    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2739465125024076468L;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * oldEntityCode:原来的实体编号. 
     * @since JDK 1.6 
     */ 
    private String oldEntityCode;
    /** 
     * newEntityCode:新的实体编号. 
     * @since JDK 1.6 
     */ 
    private String newEntityCode;
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOldEntityCode() {
        return oldEntityCode;
    }
    public void setOldEntityCode(String oldEntityCode) {
        this.oldEntityCode = oldEntityCode;
    }
    public String getNewEntityCode() {
        return newEntityCode;
    }
    public void setNewEntityCode(String newEntityCode) {
        this.newEntityCode = newEntityCode;
    }

}

