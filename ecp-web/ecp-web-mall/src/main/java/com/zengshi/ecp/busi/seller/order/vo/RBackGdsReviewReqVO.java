package com.zengshi.ecp.busi.seller.order.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.order.dubbo.dto.RBackOrdSubReq;

public class RBackGdsReviewReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3501369658320582160L;
    /** 
     * orderId:订单编号. 
     * @since JDK 1.6 
     */ 
    @NotNull(message="{order.orderId.null.error}")
    private String orderId;
    
    /** 
     * backId:申请单号. 
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
    private String shareInfo;
    
    

    public String getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(String shareInfo) {
        this.shareInfo = shareInfo;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public List<RBackOrdSubReq> getrBackOrdSubReqs() {
        return rBackOrdSubReqs;
    }

    public void setrBackOrdSubReqs(List<RBackOrdSubReq> rBackOrdSubReqs) {
        this.rBackOrdSubReqs = rBackOrdSubReqs;
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

}

