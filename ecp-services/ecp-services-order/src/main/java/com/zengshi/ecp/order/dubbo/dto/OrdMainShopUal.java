package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class OrdMainShopUal implements Serializable {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6903491556024767844L;

    private Long staffId;
    
    /** 
     * dealNum:交易次数. 
     * @since JDK 1.6 
     */ 
    private Long dealNum;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDealNum() {
        return dealNum;
    }

    public void setDealNum(Long dealNum) {
        this.dealNum = dealNum;
    }


}