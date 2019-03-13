package com.zengshi.ecp.order.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.OrdCartItemCriteria;

public interface OrdCartItemUalMapper {
    Long countSumAmountByExample(OrdCartItemCriteria example) throws DataAccessException;
}