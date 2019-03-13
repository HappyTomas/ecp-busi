package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

import com.zengshi.paas.utils.StringUtil;

public class WOrderCountInfo implements Serializable{

    private static final long serialVersionUID = 4423151262626355247L;
    
    public WOrderCountInfo()
    {
        this.allOrderCount = 0L;
        this.allOrderMoney = 0L;
        this.allOrderSellMoney = 0L;
        this.allPayedCount = 0L;
        this.placePrecent = 0.0;
    }
    
    public WOrderCountInfo addCountInfo(WOrderCountInfo countInfo)
    {
        if (countInfo == null) {
            return this;
        }
        this.allOrderCount += countInfo.getAllOrderCount();
        this.allOrderMoney += countInfo.getAllOrderMoney();
        this.allOrderSellMoney += countInfo.getAllOrderSellMoney();
        this.allPayedCount += countInfo.getAllPayedCount();
        if (!this.allOrderCount.equals(0L)) {this.placePrecent = this.allPayedCount*1.00/this.allOrderCount;}
        if (StringUtil.isNotBlank(countInfo.getDataSource())) {this.dataSource = new String(countInfo.getDataSource());}

        
       
        return this;
    }
/*
        订单额
        销售额
        订单总量
        支付成功量
        下单成功率
   */
    private Long allOrderMoney;
    private Long allOrderSellMoney;
    private Long allOrderCount;
    private Long allPayedCount;
    private Double placePrecent;
    private String dataSource;
    public Long getAllOrderMoney() {
        return allOrderMoney;
    }
    public void setAllOrderMoney(Long allOrderMoney) {
        this.allOrderMoney = allOrderMoney;
    }

    public Long getAllOrderSellMoney() {
        return allOrderSellMoney;
    }
    public void setAllOrderSellMoney(Long allOrderSellMoney) {
        this.allOrderSellMoney = allOrderSellMoney;
    }
    public Long getAllOrderCount() {
        return allOrderCount;
    }
    public void setAllOrderCount(Long allOrderCount) {
        this.allOrderCount = allOrderCount;
    }
    public Long getAllPayedCount() {
        return allPayedCount;
    }
    public void setAllPayedCount(Long allPayedCount) {
        this.allPayedCount = allPayedCount;
    }
    public Double getPlacePrecent() {
        return placePrecent;
    }
    public void setPlacePrecent(Double placePrecent) {
        this.placePrecent = placePrecent;
    }
    public String getDataSource() {
        return dataSource;
    }
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    
    
}

