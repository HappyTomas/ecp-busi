package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.distribute.tx.common.TransactionListener;

public interface IOrdRemoveCouponSV extends TransactionListener {
    /** 
     * dealMethod:优惠券域处理方法. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest 
     * @since JDK 1.6 
     */ 
    public void dealMethod(ROrderDetailsRequest rOrderDetailsRequest);
}

