package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROfflineReviewRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5237723761063037826L;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * shopId:卖家id. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    /** 
     * offlineNo:线下支付流水. 
     * @since JDK 1.6 
     */ 
    private Long offlineNo;
    /** 
     * checkCont:审核内容. 
     * @since JDK 1.6 
     */ 
    private String checkCont;
    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private String status;
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
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getOfflineNo() {
        return offlineNo;
    }
    public void setOfflineNo(Long offlineNo) {
        this.offlineNo = offlineNo;
    }
    public String getCheckCont() {
        return checkCont;
    }
    public void setCheckCont(String checkCont) {
        this.checkCont = checkCont;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

