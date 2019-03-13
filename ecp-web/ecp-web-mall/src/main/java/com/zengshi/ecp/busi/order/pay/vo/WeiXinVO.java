package com.zengshi.ecp.busi.order.pay.vo;

import java.io.Serializable;

public class WeiXinVO implements Serializable { 
    
    private static final long serialVersionUID = 1L;

    //订单ID
    private String orderIds;
    
    //合并支付的订单ID
    private String joinOrderId;
    
    //合并支付的总金额
    private long mergeTotalRealMoney;
    
    //交易类型
    private String trade_type;

    //预支付交易会话标识
    private String prepay_id;
    
    //二维码链接
    private String code_url;
    
    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public String getJoinOrderId() {
        return joinOrderId;
    }

    public void setJoinOrderId(String joinOrderId) {
        this.joinOrderId = joinOrderId;
    }
    
    public long getMergeTotalRealMoney() {
        return mergeTotalRealMoney;
    }

    public void setMergeTotalRealMoney(long mergeTotalRealMoney) {
        this.mergeTotalRealMoney = mergeTotalRealMoney;
    }
    
    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}

