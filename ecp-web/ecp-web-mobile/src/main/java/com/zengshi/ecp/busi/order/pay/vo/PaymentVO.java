package com.zengshi.ecp.busi.order.pay.vo;

import java.io.Serializable;

public class PaymentVO implements Serializable {
 
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7003522325936512237L;
    
    private String orderId;

    private String payWay;
    
    private String oper;

    /**
     * 用于合并支付的多个订单
     */
    private String orderIds;
    /**
     * 用于合并支付的多个订单
     */
    private String payWays;

    private String onlineKey;
    
    private String code;

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
    
    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public String getPayWays() {
        return payWays;
    }

    public void setPayWays(String payWays) {
        this.payWays = payWays;
    }

    public String getOnlineKey() {
        return onlineKey;
    }

    public void setOnlineKey(String onlineKey) {
        this.onlineKey = onlineKey;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

