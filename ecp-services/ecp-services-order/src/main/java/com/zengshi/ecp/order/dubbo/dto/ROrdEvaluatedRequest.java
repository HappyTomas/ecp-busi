package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdEvaluatedRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7695057637103779748L;
    
    /** 
     * orderId:主订单. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * subId:子订单. 
     * @since JDK 1.6 
     */ 
    private String subId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSubId() {
        return subId;
    }
    public void setSubId(String subId) {
        this.subId = subId;
    }

}

