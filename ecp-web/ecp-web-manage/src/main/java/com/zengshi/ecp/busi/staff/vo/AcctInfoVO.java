package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class AcctInfoVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5192731130149930483L;

    private Long staffId;

    private String acctType;

    private String adaptType;

    private Long shopId;
    
    private String staffName;
    
    private String ShopName;

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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

}

