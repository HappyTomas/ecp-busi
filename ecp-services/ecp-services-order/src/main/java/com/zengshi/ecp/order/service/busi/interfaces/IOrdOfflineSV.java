package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdOffline;
import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IOrdOfflineSV extends IGeneralSQLSV {
    
    /** 
     * saveOrdOffline:保存线下支付订单信息. <br/> 
     * @author cbl 
     * @param ordOffline 
     * @since JDK 1.6 
     */ 
    public void saveOrdOffline(OrdOffline ordOffline);
    
    /** 
     * offlineApply:线下支付申请. <br/> 
     * @author cbl 
     * @param rOfflineApplyRequest 
     * @since JDK 1.6 
     */ 
    public void saveOfflineApply(ROfflineApplyRequest rOfflineApplyRequest);
    
    /** 
     * queryOfflineReview:线下支付待审核订单查询. <br/> 
     * @author cbl 
     * @param rOfflineQueryRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROfflineQueryResponse queryOfflineReview(ROfflineQueryRequest rOfflineQueryRequest);
    
    /** 
     * queryOfflineOrder:线下支付订单查询. <br/> 
     * @author cbl 
     * @param rOfflineQueryRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROfflineQueryResponse> queryOfflineOrder(ROfflineQueryRequest rOfflineQueryRequest);

    /**
     * queryCheckedOrder:线下支付已审核订单查询. <br/>
     * @author wxq
     * @param rOfflineQueryRequest
     * @return
     * @since JDK 1.6
     */
    public PageResponseDTO<ROfflineQueryResponse> queryCheckedOrder(ROfflineQueryRequest rOfflineQueryRequest);

    /** 
     * saveOrdOffline:更新线下支付订单信息状态. <br/> 
     * @author cbl 
     * @param ordOffline 
     * @since JDK 1.6 
     */ 
    public void updateOrdOfflineStatus(ROfflineReviewRequest rOfflineReviewRequest);
    
    /** 
     * deleteOrdOffline:根据订单号删除审核申请记录. <br/> 
     * @author cbl 
     * @param orderId 
     * @since JDK 1.6 
     */ 
    public void deleteOrdOffline(ROrderDetailsRequest rOrderDetailsRequest);
}

