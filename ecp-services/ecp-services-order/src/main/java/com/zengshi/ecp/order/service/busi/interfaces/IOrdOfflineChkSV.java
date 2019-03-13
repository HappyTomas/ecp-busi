package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdOfflineChk;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;

public interface IOrdOfflineChkSV {
    
    /** 
     * saveOrdOfflineChk:保存审核对象信息. <br/> 
     * @author cbl 
     * @param ordOfflineChk 
     * @since JDK 1.6 
     */ 
    public void saveOrdOfflineChk(OrdOfflineChk ordOfflineChk);

    /** 
     * saveOfflineReview:线下支付审核信息保存. <br/> 
     * @author cbl 
     * @param rOfflineReviewRequest 
     * @since JDK 1.6 
     */ 
    public void saveOfflineReview(ROfflineReviewRequest rOfflineReviewRequest);
}

