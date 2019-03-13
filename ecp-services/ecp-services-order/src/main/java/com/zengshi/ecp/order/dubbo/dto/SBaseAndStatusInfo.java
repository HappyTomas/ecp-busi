package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:09:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class SBaseAndStatusInfo extends SBaseSplitInfo{

    /** 
     * status:主订单状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    
    /** 
     * orderTwoStatus:主单二级状态. 
     * @since JDK 1.6 
     */ 
    private String orderTwoStatus;
    
    /** 
     * deliverDate:收货时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp deliverDate;
    
    public String getOrderTwoStatus() {
        return orderTwoStatus;
    }
    public void setOrderTwoStatus(String orderTwoStatus) {
        this.orderTwoStatus = orderTwoStatus;
    }
    public Timestamp getDeliverDate() {
        return deliverDate;
    }
    public void setDeliverDate(Timestamp deliverDate) {
        this.deliverDate = deliverDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

