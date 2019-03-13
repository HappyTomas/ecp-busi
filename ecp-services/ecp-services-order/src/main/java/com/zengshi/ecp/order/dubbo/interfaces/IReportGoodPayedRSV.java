package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IReportGoodPayedRSV {
    /** 
     * querySumBuyNumBySkuId:商品销售量查询. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public Long querySumBuyNumBySkuId(RQueryGoodPayedRequest rQueryGoodPayedRequest) throws BusinessException;
    
    /** 
     * querySkuSalesChart:查询销量前几位的单品. <br/> 
     * @author cbl 
     * @param rSalesChartRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RSalesChartResponse querySkuSalesChart(RSalesChartRequest rSalesChartRequest) throws BusinessException;

    /**
     *
     * @param rQueryGoodPayedRequest
     * @return
     * @author wxq
     * @throws BusinessException
     */
    public Long querySumBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest) throws BusinessException;
    
    /** 
     * queryStaffTradeTimes:查找出在店铺发生交易次数一定数量的用户. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdStaffTradeInfoResp queryStaffTradeTimes(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq) throws BusinessException;
    
    /** 
     * querySumBuyNumByShopId:统计店铺销售总商品数量. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public Long querySumBuyNumByShopId(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;
    /** 
     * querySumBuyNumBySkuId:商品销售量查询. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */   
    public Long queryStaffBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException;
}

