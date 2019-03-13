package com.zengshi.ecp.order.dubbo.dto;

public class SRefundInfo {
    
    /** 
     * refundStatus:退款标识. 
     * @since JDK 1.6 
     */ 
    private String refundStatus;
    
    /** 
     * refundId:退款ID. 
     * @since JDK 1.6 
     */ 
    private Long refundId;

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }
    
    
}

