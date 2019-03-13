package com.zengshi.ecp.staff.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrderAcctMainResDTO<T extends BaseInfo> extends BaseInfo {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -338996087612618315L;
    
    
    private List<T> list;
    
    private String orderId;
    
    private long totalMoney;
    
    private long backTotalMoney;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getBackTotalMoney() {
        return backTotalMoney;
    }

    public void setBackTotalMoney(long backTotalMoney) {
        this.backTotalMoney = backTotalMoney;
    }
    
}

