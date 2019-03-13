package com.zengshi.ecp.busi.order.pay.vo;

import java.io.Serializable;
import java.util.List;

public class ROfflineApplyReqVO implements Serializable {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5679640713400304259L;
    
    String               orderId; 
    Long                 shopId ; 
    Long                 staffId; 
    String               remark ; 
    List<SOfflinePicVO>  annex  ;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public List<SOfflinePicVO> getAnnex() {
        return annex;
    }
    public void setAnnex(List<SOfflinePicVO> annex) {
        this.annex = annex;
    } 
    
    
}

