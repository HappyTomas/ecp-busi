package com.zengshi.ecp.order.dao.mapper.busi.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.GoodPayedReport;
import com.zengshi.ecp.order.dao.model.GoodPayedReportCriteria;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;

public interface GoodPayedReportUalMapper {
    Long countSumBuyNumByExample(GoodPayedReportCriteria example) throws DataAccessException;
    
    List<GoodPayedReport> querySkuSalesChart(RSalesChartRequest rSalesChartRequest) throws DataAccessException;
}