package com.zengshi.ecp.order.dubbo.dto.pay;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RPayRefundRequest extends BaseInfo {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 支付通道
     */
    private String payWay;
    
    /**
     * 退货申请id
     */
    private Long backId;
    
    /**
     * 退款金额
     */
    private Long refundAmount;

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

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }


}

