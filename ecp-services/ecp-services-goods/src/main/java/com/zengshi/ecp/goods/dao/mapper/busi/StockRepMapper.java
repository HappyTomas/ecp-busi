package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockRep;
import com.zengshi.ecp.goods.dao.model.StockRepCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockRepMapper {
    Long countByExample(StockRepCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockRep record) throws DataAccessException;

    int insertSelective(StockRep record) throws DataAccessException;

    List<StockRep> selectByExample(StockRepCriteria example) throws DataAccessException;

    StockRep selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockRep record, @Param("example") StockRepCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockRep record, @Param("example") StockRepCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockRep record) throws DataAccessException;

    int updateByPrimaryKey(StockRep record) throws DataAccessException;
}
