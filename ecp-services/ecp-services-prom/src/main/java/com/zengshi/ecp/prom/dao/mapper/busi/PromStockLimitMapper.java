package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimitCriteria;
import com.zengshi.ecp.prom.dao.model.PromStockLimitKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromStockLimitMapper {
    Long countByExample(PromStockLimitCriteria example) throws DataAccessException;

    int deleteByExample(PromStockLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PromStockLimitKey key) throws DataAccessException;

    int insert(PromStockLimit record) throws DataAccessException;

    int insertSelective(PromStockLimit record) throws DataAccessException;

    List<PromStockLimit> selectByExample(PromStockLimitCriteria example) throws DataAccessException;

    PromStockLimit selectByPrimaryKey(PromStockLimitKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromStockLimit record, @Param("example") PromStockLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromStockLimit record, @Param("example") PromStockLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromStockLimit record) throws DataAccessException;

    int updateByPrimaryKey(PromStockLimit record) throws DataAccessException;
}