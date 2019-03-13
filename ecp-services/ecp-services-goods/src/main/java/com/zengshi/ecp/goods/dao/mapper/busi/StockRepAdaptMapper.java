package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockRepAdapt;
import com.zengshi.ecp.goods.dao.model.StockRepAdaptCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockRepAdaptMapper {
    Long countByExample(StockRepAdaptCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockRepAdapt record) throws DataAccessException;

    int insertSelective(StockRepAdapt record) throws DataAccessException;

    List<StockRepAdapt> selectByExample(StockRepAdaptCriteria example) throws DataAccessException;

    StockRepAdapt selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockRepAdapt record, @Param("example") StockRepAdaptCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockRepAdapt record, @Param("example") StockRepAdaptCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockRepAdapt record) throws DataAccessException;

    int updateByPrimaryKey(StockRepAdapt record) throws DataAccessException;
}
