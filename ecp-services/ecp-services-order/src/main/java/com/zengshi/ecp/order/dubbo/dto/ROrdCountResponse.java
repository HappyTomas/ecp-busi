package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdCountResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1760066717294867213L;
    
    /** 
     * requestStatusAll:全部订单数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusAll;
    /** 
     * requestStatusPay:待支付数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusPay;
    /** 
     * requestStatusSend:待发货数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusSend;
    /** 
     * requestStatusRecept:待收货数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusRecept;
    /** 
     * requestStatusReceptde:已收货数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusReceptde;
    /** 
     * requestStatusCancle:已取消数量. 
     * @since JDK 1.6 
     */ 
    private Long requestStatusCancle;
    public Long getRequestStatusAll() {
        return requestStatusAll;
    }
    public void setRequestStatusAll(Long requestStatusAll) {
        this.requestStatusAll = requestStatusAll;
    }
    public Long getRequestStatusPay() {
        return requestStatusPay;
    }
    public void setRequestStatusPay(Long requestStatusPay) {
        this.requestStatusPay = requestStatusPay;
    }
    public Long getRequestStatusSend() {
        return requestStatusSend;
    }
    public void setRequestStatusSend(Long requestStatusSend) {
        this.requestStatusSend = requestStatusSend;
    }
    public Long getRequestStatusRecept() {
        return requestStatusRecept;
    }
    public void setRequestStatusRecept(Long requestStatusRecept) {
        this.requestStatusRecept = requestStatusRecept;
    }
    public Long getRequestStatusReceptde() {
        return requestStatusReceptde;
    }
    public void setRequestStatusReceptde(Long requestStatusReceptde) {
        this.requestStatusReceptde = requestStatusReceptde;
    }
    public Long getRequestStatusCancle() {
        return requestStatusCancle;
    }
    public void setRequestStatusCancle(Long requestStatusCancle) {
        this.requestStatusCancle = requestStatusCancle;
    }

}

