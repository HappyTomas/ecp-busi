package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockInfoAdapt;
import com.zengshi.ecp.goods.dao.model.StockInfoAdaptCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockInfoAdaptMapper {
    Long countByExample(StockInfoAdaptCriteria example) throws DataAccessException;
    
    int deleteByExample(StockInfoAdaptCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockInfoAdapt record) throws DataAccessException;

    int insertSelective(StockInfoAdapt record) throws DataAccessException;

    List<StockInfoAdapt> selectByExample(StockInfoAdaptCriteria example) throws DataAccessException;

    StockInfoAdapt selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockInfoAdapt record, @Param("example") StockInfoAdaptCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockInfoAdapt record, @Param("example") StockInfoAdaptCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockInfoAdapt record) throws DataAccessException;

    int updateByPrimaryKey(StockInfoAdapt record) throws DataAccessException;
}
