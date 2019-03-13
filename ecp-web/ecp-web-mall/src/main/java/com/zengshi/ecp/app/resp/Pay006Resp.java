package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;

import com.zengshi.butterfly.app.model.IBody;

public class Pay006Resp extends IBody {

    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private boolean status;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * payTime:支付时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp payTime;
    /** 
     * siteId:站点ID. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Timestamp getPayTime() {
        return payTime;
    }
    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    
}

