package com.zengshi.ecp.order.dubbo.dto.pay;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RPayRefundResponse extends BaseResponseDTO {

    /**
     * 退款方式 ：01 不经页面跳转 ，02 经页面跳转到支付公司页面进行操作
     */
    private String refundMethod;
    /**
     * 请求表单信息
     */
    private PayRequestData payRequestData;
    
    /**
     * 实时退款请求时成功标识
     */
    private boolean flag;
    
    /**
     * 退款响应信息
     */
    private String message;
    
    /**
     * 缓存KEY
     */
    private String sourceKey;

    private static final long serialVersionUID = 1L;

    public String getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod;
    }

    public PayRequestData getPayRequestData() {
        return payRequestData;
    }

    public void setPayRequestData(PayRequestData payRequestData) {
        this.payRequestData = payRequestData;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

}