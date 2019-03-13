package com.zengshi.ecp.order.dubbo.dto;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月12日上午10:09:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class SDelyAmountInfo {
    
    
    /** 
     * RemainAmounts:订单剩余发货数量. 
     * @since JDK 1.6 
     */ 
    private Long RemainAmounts;
    /** 
     * DeliverAmounts:订单已发货数量. 
     * @since JDK 1.6 
     */ 
    private Long DeliverAmounts;
    public Long getRemainAmounts() {
        return RemainAmounts;
    }
    public void setRemainAmounts(Long remainAmounts) {
        RemainAmounts = remainAmounts;
    }
    public Long getDeliverAmounts() {
        return DeliverAmounts;
    }
    public void setDeliverAmounts(Long deliverAmounts) {
        DeliverAmounts = deliverAmounts;
    }

}

