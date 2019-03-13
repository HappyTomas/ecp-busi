package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pay006Req extends IBody {
    
    /** 
     * payWay:支付方式. 
     * @since JDK 1.6 
     */ 
    private String payWay;
    /** 
     * actionUrl:请求支付地址. 
     * @since JDK 1.6 
     */ 
    private String actionUrl;
    /**
     * status 支付结果状态
     */
    private String status;
    /**
     * message 支付结果说明
     */
    private String message;
    /**
     * 支付结果展示链接
     */
    private String payReturnUrl;
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    public String getActionUrl() {
        return actionUrl;
    }
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayReturnUrl() {
        return payReturnUrl;
    }

    public void setPayReturnUrl(String payReturnUrl) {
        this.payReturnUrl = payReturnUrl;
    }
}

