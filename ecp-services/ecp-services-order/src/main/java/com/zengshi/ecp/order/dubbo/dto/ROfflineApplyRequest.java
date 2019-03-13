package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROfflineApplyRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1350364416080264480L;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    
    
    /** 
     * applyType:申请类型：线下支付/上门支付. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    /** 
     * remark:备注. 
     * @since JDK 1.6 
     */ 
    private String remark;
    /** 
     * annex:附件. 
     * @since JDK 1.6 
     */ 
    List<SOfflinePic> annex;
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
    public List<SOfflinePic> getAnnex() {
        return annex;
    }
    public void setAnnex(List<SOfflinePic> annex) {
        this.annex = annex;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}

