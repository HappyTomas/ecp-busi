package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pay104Req extends IBody {
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;

    /** 
     * payWay:支付方式. 
     * @since JDK 1.6 
     */ 
    private String payWay;
    
    /** 
     * clientIp:客户端ID. 
     * @since JDK 1.6 
     */ 
    private String clientIp;
    
    /** 
     * staffId:当前用户ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    
    
}

