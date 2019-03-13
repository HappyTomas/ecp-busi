package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackDiscount;
import com.zengshi.ecp.order.dao.model.OrdBackDiscountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackDiscountMapper {
    Long countByExample(OrdBackDiscountCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackDiscountCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackDiscount record) throws DataAccessException;

    int insertSelective(OrdBackDiscount record) throws DataAccessException;

    List<OrdBackDiscount> selectByExample(OrdBackDiscountCriteria example) throws DataAccessException;

    OrdBackDiscount selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackDiscount record, @Param("example") OrdBackDiscountCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackDiscount record, @Param("example") OrdBackDiscountCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackDiscount record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackDiscount record) throws DataAccessException;
}