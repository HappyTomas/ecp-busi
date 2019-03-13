package com.zengshi.ecp.order.dubbo.dto;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:09:57  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class SBaseAndSubInfo extends SBaseSplitInfo{
    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;
    public String getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
}

