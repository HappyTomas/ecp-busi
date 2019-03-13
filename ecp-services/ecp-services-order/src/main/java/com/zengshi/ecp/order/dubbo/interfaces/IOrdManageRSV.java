package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderSummaryResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdManageRSV {
    /** 
     * queryOrder:管理平台查询订单信息. <br/> 
     * @author cbl 
     * @param rQueryOrdetrRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShopOrderResponse> queryOrder(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    /** 
     * queryOrderSummaryData:订单查询汇总信息查询. <br/> 
     * @author cbl 
     * @param queryOrderRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrderSummaryResponse queryOrderSummaryData(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

    /**
     * queryOrderCountByShop:统计订单各种状态数量
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     */
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
}

