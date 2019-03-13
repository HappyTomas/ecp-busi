package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class TransactionContentReqDTO extends BaseInfo implements Serializable {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private String orderId;
    
    private Long staffId;
    
    private String acctType;
    
    private String adaptType;
    
    private Long shopId;
    
    private String debitCredit;
    
    private String tradeType;
    
    private Long tradeMoney; //交易金额
    
    private Long orderMoney; //订单总金额

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

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Long getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Long tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", staffId=").append(staffId);
        sb.append(", acctType=").append(acctType);
        sb.append(", adaptType=").append(adaptType);
        sb.append(", shopId=").append(shopId);
        sb.append(", debitCredit=").append(debitCredit);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeMoney=").append(tradeMoney);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append("]");
        return sb.toString();
    }
    
    
    
}

