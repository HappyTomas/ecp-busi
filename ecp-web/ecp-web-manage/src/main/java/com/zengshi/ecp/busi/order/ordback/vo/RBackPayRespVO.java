package com.zengshi.ecp.busi.order.ordback.vo;

import java.io.Serializable;

import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;

public class RBackPayRespVO implements Serializable {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5588249895086048600L;
    /**
     * 退款方式 ：01 不经页面跳转 ，02 经页面跳转到支付公司页面进行操作
     */
    private String refundMethod;
    /** 
     * resultFlag:返回结果标识. 
     * @since JDK 1.6 
     */ 
    private String resultFlag;
    /** 
     * resultMsg:返回信息. 
     * @since JDK 1.6 
     */ 
    private String resultMsg;;
    /** 
     * rPayRefundResponse:支付返回,跳转页面入参对象. 
     * @since JDK 1.6 
     */ 
    private PayRequestData payRequestData;
    
    
    public String getRefundMethod() {
        return refundMethod;
    }
    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod;
    }
    public String getResultFlag() {
        return resultFlag;
    }
    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }
    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    public PayRequestData getPayRequestData() {
        return payRequestData;
    }
    public void setPayRequestData(PayRequestData payRequestData) {
        this.payRequestData = payRequestData;
    }
    
}

