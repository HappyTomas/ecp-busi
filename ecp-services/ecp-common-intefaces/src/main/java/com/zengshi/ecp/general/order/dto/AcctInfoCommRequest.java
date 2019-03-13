package com.zengshi.ecp.general.order.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;


public class AcctInfoCommRequest extends BaseInfo{
    private Long deductOrderMoney;//订单抵扣金额
    
    private Long staffId;

    private String acctType;
    
    private String adaptType;
   
    private Long shopId;
    

    public Long getDeductOrderMoney() {
        return deductOrderMoney;
    }

    public void setDeductOrderMoney(Long deductOrderMoney) {
        this.deductOrderMoney = deductOrderMoney;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getAdaptType() {
        return adaptType;
    }

    public void setAdaptType(String adaptType) {
        this.adaptType = adaptType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    


}
