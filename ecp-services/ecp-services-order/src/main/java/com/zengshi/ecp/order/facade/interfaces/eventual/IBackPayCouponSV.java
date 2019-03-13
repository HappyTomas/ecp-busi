package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.distribute.tx.common.TransactionListener;

public interface IBackPayCouponSV extends TransactionListener {
    /** 
     * dealMethod:调用优惠卷域处理方法. <br/> 
     * @author cbl 
     * @param rBackConfirmReq 
     * @since JDK 1.6 
     */ 
    public void dealMethod(RBackConfirmReq rBackConfirmReq);
}

