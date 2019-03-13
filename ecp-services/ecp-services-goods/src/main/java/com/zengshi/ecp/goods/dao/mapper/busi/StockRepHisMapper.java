package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockRepHis;
import com.zengshi.ecp.goods.dao.model.StockRepHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockRepHisMapper {
    Long countByExample(StockRepHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockRepHis record) throws DataAccessException;

    int insertSelective(StockRepHis record) throws DataAccessException;

    List<StockRepHis> selectByExample(StockRepHisCriteria example) throws DataAccessException;

    StockRepHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockRepHis record, @Param("example") StockRepHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockRepHis record, @Param("example") StockRepHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockRepHis record) throws DataAccessException;

    int updateByPrimaryKey(StockRepHis record) throws DataAccessException;
}
