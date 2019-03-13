package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pay005Req extends IBody {
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * staffId:当前用户ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    
    /** 
     * checkCode:校验编码. 
     * @since JDK 1.6 
     */ 
    private String checkCode;
    
    
    public String getCheckCode() {
        return checkCode;
    }
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

}

