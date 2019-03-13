package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Ord107Req extends IBody {
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

