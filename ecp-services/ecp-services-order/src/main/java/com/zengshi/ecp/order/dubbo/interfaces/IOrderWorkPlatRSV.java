package com.zengshi.ecp.order.dubbo.interfaces;

public interface IOrderWorkPlatRSV {

    //1.获取平台代发货总数
    public long catchPlatDeliverCount();
    
    //2.获取平台线下支付审核总数
    public long catchPlatOfflineCheckCount();
    
    //3.获取平台待处理退货订单总数
    public long catchPlatBackGdsOrderCount();
    
    //4.获取平台待处理退款订单总数
    public long catchPlatBackMoneyOrderCount();
}

