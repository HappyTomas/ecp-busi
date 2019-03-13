package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackCouponResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3528838239119583498L;

    /** 
     * backId:退货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * orderId:订单ID. 
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
     * couponTypeId:优惠券类型编码. 
     * @since JDK 1.6 
     */ 
    private Long couponTypeId;

    /** 
     * couponTypeName:优惠券类型名称. 
     * @since JDK 1.6 
     */ 
    private String couponTypeName;

    /** 
     * couponCnt:优惠券数量. 
     * @since JDK 1.6 
     */ 
    private Long couponCnt;

    /** 
     * couponAmount:优惠卷面额. 
     * @since JDK 1.6 
     */ 
    private Long couponAmount;

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

    public Long getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(Long couponTypeId) {
        this.couponTypeId = couponTypeId;
    }
    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName == null ? null : couponTypeName.trim();
    }

    public Long getCouponCnt() {
        return couponCnt;
    }

    public void setCouponCnt(Long couponCnt) {
        this.couponCnt = couponCnt;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }
}

