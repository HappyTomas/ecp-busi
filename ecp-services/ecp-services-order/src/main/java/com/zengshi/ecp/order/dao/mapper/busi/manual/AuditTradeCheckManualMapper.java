package com.zengshi.ecp.order.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.AuditTradeCheckCriteria;

public interface AuditTradeCheckManualMapper {
    Long countSumTransAmountByExample(AuditTradeCheckCriteria example) throws DataAccessException;
    
    Long countSumOrderMoneyByExample(AuditTradeCheckCriteria example) throws DataAccessException;
    
    Long countSumRefundTransAmountByExample(AuditTradeCheckCriteria example) throws DataAccessException;
    
    Long countSumRefundOrderAmountByExample(AuditTradeCheckCriteria example) throws DataAccessException;
}