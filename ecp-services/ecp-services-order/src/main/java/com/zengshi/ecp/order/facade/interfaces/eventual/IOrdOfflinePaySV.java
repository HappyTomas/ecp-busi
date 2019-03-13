package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;

public interface IOrdOfflinePaySV{
    /** 
     * saveOfflineReview:线下支付审核通过信息保存. <br/> 
     * @author cbl 
     * @param rOfflineReviewRequest 
     * @since JDK 1.6 
     */ 
    public void saveOfflineReviewPass(ROfflineReviewRequest rOfflineReviewRequest);
    
    /** 
     * saveOfflineReview:线下支付审核通过信息保存. <br/> 
     * @author cbl 
     * @param rOfflineReviewRequest 
     * @since JDK 1.6 
     */ 
    public void saveOfflineReviewCancle(ROfflineReviewRequest rOfflineReviewRequest);
}

