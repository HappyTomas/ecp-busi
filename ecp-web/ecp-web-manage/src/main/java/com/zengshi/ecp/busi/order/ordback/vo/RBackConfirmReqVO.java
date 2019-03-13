package com.zengshi.ecp.busi.order.ordback.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RBackConfirmReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5499597504243600174L;
    /** 
     * backId:退换货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * applyType:审核类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    
    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    
    /** 
     * backPicList:上传图片id串. 
     * @since JDK 1.6 
     */ 
    private List<String> backPicList;

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getBackPicList() {
        return backPicList;
    }

    public void setBackPicList(List<String> backPicList) {
        this.backPicList = backPicList;
    }
    
    
}

