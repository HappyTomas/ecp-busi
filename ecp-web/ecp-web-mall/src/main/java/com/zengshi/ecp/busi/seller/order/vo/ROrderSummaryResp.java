package com.zengshi.ecp.busi.seller.order.vo;

import org.apache.poi.ss.formula.functions.T;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;

public class ROrderSummaryResp extends EcpBasePageRespVO<T> {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 360197932631695738L;
    /** 
     * sumOrderMoney:订单总应付金额. 
     * @since JDK 1.6 
     */ 
    private Long sumOrderMoney;
    /** 
     * sumRealMoney:订单总实付金额. 
     * @since JDK 1.6 
     */ 
    private Long sumRealMoney;
    /** 
     * orderCount:总订单数量. 
     * @since JDK 1.6 
     */ 
    private Long orderCount;
    /** 
     * payedCount:已支付定订单数量. 
     * @since JDK 1.6 
     */ 
    private Long payedCount;
    /** 
     * payedRate:订单成交率. 
     * @since JDK 1.6 
     */ 
    private Long payedRate;
    public Long getSumOrderMoney() {
        return sumOrderMoney;
    }
    public void setSumOrderMoney(Long sumOrderMoney) {
        this.sumOrderMoney = sumOrderMoney;
    }
    public Long getSumRealMoney() {
        return sumRealMoney;
    }
    public void setSumRealMoney(Long sumRealMoney) {
        this.sumRealMoney = sumRealMoney;
    }
    public Long getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
    public Long getPayedCount() {
        return payedCount;
    }
    public void setPayedCount(Long payedCount) {
        this.payedCount = payedCount;
    }
    public Long getPayedRate() {
        return payedRate;
    }
    public void setPayedRate(Long payedRate) {
        this.payedRate = payedRate;
    }
}

