package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.distribute.tx.common.TransactionListener;

public interface IBackPayGoodsSV extends TransactionListener {
    /** 
     * dealMethod:调用商品域处理方法. <br/> 
     * @author cbl 
     * @param rBackConfirmReq 
     * @since JDK 1.6 
     */ 
    public void dealMethod(RBackConfirmReq rBackConfirmReq);
}

