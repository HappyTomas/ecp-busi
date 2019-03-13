package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockInfoHis;
import com.zengshi.ecp.goods.dao.model.StockInfoHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockInfoHisMapper {
    Long countByExample(StockInfoHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockInfoHis record) throws DataAccessException;

    int insertSelective(StockInfoHis record) throws DataAccessException;

    List<StockInfoHis> selectByExample(StockInfoHisCriteria example) throws DataAccessException;

    StockInfoHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockInfoHis record, @Param("example") StockInfoHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockInfoHis record, @Param("example") StockInfoHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockInfoHis record) throws DataAccessException;

    int updateByPrimaryKey(StockInfoHis record) throws DataAccessException;
}
