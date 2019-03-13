package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdOfflineRSV {

    /** 
     * queryOfflinePay:线下支付查询. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public ROfflinePayResponse queryOfflinePay(ROfflinePayRequest rOfflinePayRequest) throws BusinessException;
    /** 
     * offlineApply:线下支付申请. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void offlineApply(ROfflineApplyRequest rOfflineApplyRequest) throws BusinessException;
    /** 
     * queryOfflineReview:线下支付审核查询. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public ROfflineQueryResponse queryOfflineReview(ROfflineQueryRequest rOfflineQueryRequest) throws BusinessException;
    /** 
     * OfflineReview:线下支付审核. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void OfflineReview(ROfflineReviewRequest rOfflineReviewRequest) throws BusinessException;
    
    /** 
     * queryOfflineReview:待线下支付审核订单查询. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROfflineQueryResponse> queryOfflineOrder(ROfflineQueryRequest rOfflineQueryRequest) throws BusinessException;

    /**
     * queryOfflineReview:待线下支付已审核订单查询. <br/>
     * @author wxq
     * @since JDK 1.6
     */
    public PageResponseDTO<ROfflineQueryResponse> queryCheckedOrder(ROfflineQueryRequest rOfflineQueryRequest) throws BusinessException;
}

