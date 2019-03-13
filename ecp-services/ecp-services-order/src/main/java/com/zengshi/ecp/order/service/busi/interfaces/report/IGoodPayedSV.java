package com.zengshi.ecp.order.service.busi.interfaces.report;

import com.zengshi.ecp.order.dao.model.GoodPayedReport;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IGoodPayedSV extends IGeneralSQLSV {

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
     * saveGoodPayedReport:(这里用一句话描述这个方法的作用). <br/> 
     * @author cbl 
     * @param goodPayedReport
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveGoodPayedReport(GoodPayedReport goodPayedReport) throws BusinessException;
}

