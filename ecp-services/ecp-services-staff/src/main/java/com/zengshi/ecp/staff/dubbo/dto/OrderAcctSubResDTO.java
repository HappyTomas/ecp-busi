package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrderAcctSubResDTO extends BaseInfo {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Long staffId;
    
    private String acctType;//账户类型
    
    private String acctTypeName;//账户类型名称
    
    private String adaptType;//适用类型
    
    private Long shopId;//店铺id
    
    private Long useMoney;//使用的资金
    
    private Long backMoney;//返还的资金

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

    public Long getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(Long useMoney) {
        this.useMoney = useMoney;
    }

    public Long getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Long backMoney) {
        this.backMoney = backMoney;
    }

    public String getAcctTypeName() {
        return acctTypeName;
    }

    public void setAcctTypeName(String acctTypeName) {
        this.acctTypeName = acctTypeName;
    }
    
}