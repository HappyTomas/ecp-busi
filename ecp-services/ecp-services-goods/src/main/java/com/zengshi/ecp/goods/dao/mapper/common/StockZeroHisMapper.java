package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.StockZeroHis;
import com.zengshi.ecp.goods.dao.model.StockZeroHisCriteria;
import com.zengshi.ecp.goods.dao.model.StockZeroHisKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockZeroHisMapper {
    Long countByExample(StockZeroHisCriteria example) throws DataAccessException;

    int deleteByExample(StockZeroHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(StockZeroHisKey key) throws DataAccessException;

    int insert(StockZeroHis record) throws DataAccessException;

    int insertSelective(StockZeroHis record) throws DataAccessException;

    List<StockZeroHis> selectByExample(StockZeroHisCriteria example) throws DataAccessException;

    StockZeroHis selectByPrimaryKey(StockZeroHisKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockZeroHis record, @Param("example") StockZeroHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockZeroHis record, @Param("example") StockZeroHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockZeroHis record) throws DataAccessException;

    int updateByPrimaryKey(StockZeroHis record) throws DataAccessException;
}
