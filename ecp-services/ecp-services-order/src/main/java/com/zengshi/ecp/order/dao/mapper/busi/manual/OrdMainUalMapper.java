package com.zengshi.ecp.order.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.OrdMainCriteria;

public interface OrdMainUalMapper {
    Long countSumOrderMoneyByExample(OrdMainCriteria example) throws DataAccessException;
    
    Long countSumRealMoneyByExample(OrdMainCriteria example) throws DataAccessException;
}