package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBackReviewReq extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8954478648886501139L;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * backId:退货申请单号. 
     * @since JDK 1.6 
     */ 
    private Long backId;
    
    /** 
     * checkDesc:审核意见. 
     * @since JDK 1.6 
     */ 
    private String checkDesc;
    
    /** 
     * status:审核状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    
    /** 
     * payType:支付方式. 
     * @since JDK 1.6 
     */ 
    private String payType;
    
    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    
    /** 
     * rBackOrdSubReqs:明细列表. 
     * @since JDK 1.6 
     */ 
    private List<RBackOrdSubReq> rBackOrdSubReqs;
    
    /** 
     * rBackApplyInfoResp:分摊信息. 
     * @since JDK 1.6 
     */ 
    private RBackApplyInfoResp rBackApplyInfoResp;
    
    
    public RBackApplyInfoResp getrBackApplyInfoResp() {
        return rBackApplyInfoResp;
    }

    public void setrBackApplyInfoResp(RBackApplyInfoResp rBackApplyInfoResp) {
        this.rBackApplyInfoResp = rBackApplyInfoResp;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RBackOrdSubReq> getrBackOrdSubReqs() {
        return rBackOrdSubReqs;
    }

    public void setrBackOrdSubReqs(List<RBackOrdSubReq> rBackOrdSubReqs) {
        this.rBackOrdSubReqs = rBackOrdSubReqs;
    }
    

}

