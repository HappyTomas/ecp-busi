package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackGiftResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 771058221409925466L;
    
    /** 
     * backId:退货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private Long orderId;

    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private Long orderSubId;

    /** 
     * procType:处理类型. 
     * @since JDK 1.6 
     */ 
    private String procType;

    /** 
     * giftId:赠品编码. 
     * @since JDK 1.6 
     */ 
    private Long giftId;

    /** 
     * giftCount:赠品数量. 
     * @since JDK 1.6 
     */ 
    private Long giftCount;

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType == null ? null : procType.trim();
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

}

