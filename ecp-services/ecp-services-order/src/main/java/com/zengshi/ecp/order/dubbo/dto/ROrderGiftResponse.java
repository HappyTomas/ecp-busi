package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrderGiftResponse extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5664083413594226453L;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    private Long giftId;

    private Long giftCount;
    
    private String giftName;
    
    
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getGiftId() {
        return giftId;
    }
    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }
    public Long getGiftCount() {
        return giftCount;
    }
    public void setGiftCount(Long giftCount) {
        this.giftCount = giftCount;
    }
    public String getGiftName() {
        return giftName;
    }
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    
}

