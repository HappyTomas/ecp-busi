package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;

public interface IOrdRemoveOrderSV {
    /** 
     * dealMethod:取消订单订单域处理方法. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest 
     * @since JDK 1.6 
     */ 
    public void dealMethod(ROrderDetailsRequest rOrderDetailsRequest);
}

