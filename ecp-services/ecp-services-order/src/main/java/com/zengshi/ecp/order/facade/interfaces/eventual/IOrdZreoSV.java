package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.distribute.tx.common.TransactionListener;

public interface IOrdZreoSV{
    
    /** 
     * payZeroOrder:支付0元订单. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void payZeroOrder(final ROfflineReviewRequest rOfflineReviewRequest);

}

