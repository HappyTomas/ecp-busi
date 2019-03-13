package com.zengshi.ecp.order.dubbo.dto;

public class SCustomerOrdMain extends SOrderDetailsMain{

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6126957567871017053L;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * refundStatus:退款状态. 
     * @since JDK 1.6 
     */ 
    private String refundStatus;
    
    /** 
     * refundId:退款编号. 
     * @since JDK 1.6 
     */ 
    private Long refundId;
    
    /** 
     * backGdsStatus:退货状态. 
     * @since JDK 1.6 
     */ 
    private String backGdsStatus;
    
    /** 
     * backGdsId:退货编号. 
     * @since JDK 1.6 
     */ 
    private Long backGdsId;
    
    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getBackGdsId() {
        return backGdsId;
    }

    public void setBackGdsId(Long backGdsId) {
        this.backGdsId = backGdsId;
    }

    public String getBackGdsStatus() {
        return backGdsStatus;
    }

    public void setBackGdsStatus(String backGdsStatus) {
        this.backGdsStatus = backGdsStatus;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

