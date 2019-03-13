package com.zengshi.ecp.order.dao.mapper.busi.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.OrdSubCriteria;
import com.zengshi.ecp.order.dao.model.OrdSubOrderIdUal;

public interface OrdSubUalMapper {
    Long countRemainAmountByExample(OrdSubCriteria example) throws DataAccessException;

    List<OrdSubOrderIdUal> selectOrderIdByExample(OrdSubCriteria example) throws DataAccessException;
    
    Long countOrderIdByExample(OrdSubCriteria example) throws DataAccessException;
    
    Long sumDiscountMoneyByExample(OrdSubCriteria example) throws DataAccessException;

    Long sumOrderAmountByExample(OrdSubCriteria example) throws DataAccessException;

    Long sumBasicMoneyByExample(OrdSubCriteria example) throws DataAccessException;
    
    Long countEvaluationWait(OrdSubCriteria example) throws DataAccessException;

}