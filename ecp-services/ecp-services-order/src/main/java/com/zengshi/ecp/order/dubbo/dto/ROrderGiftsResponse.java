package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrderGiftsResponse extends BaseInfo {

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
    private List<ROrderGiftResponse> orderGifts;
    public List<ROrderGiftResponse> getOrderGifts() {
        return orderGifts;
    }
    public void setOrderGifts(List<ROrderGiftResponse> orderGifts) {
        this.orderGifts = orderGifts;
    }
    


    
}

