package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;

public interface IBackPayOrderSV {
    /** 
     * dealMethod:退货退款主方法. <br/> 
     * @author cbl 
     * @param rBackConfirmReq 
     * @since JDK 1.6 
     */ 
    public void dealMethod(RBackConfirmReq rBackConfirmReq);
}

