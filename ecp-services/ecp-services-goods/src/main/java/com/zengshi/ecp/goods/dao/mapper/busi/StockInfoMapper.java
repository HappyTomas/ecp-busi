package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockInfo;
import com.zengshi.ecp.goods.dao.model.StockInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockInfoMapper {
    Long countByExample(StockInfoCriteria example) throws DataAccessException;

    int deleteByExample(StockInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockInfo record) throws DataAccessException;

    int insertSelective(StockInfo record) throws DataAccessException;

    List<StockInfo> selectByExample(StockInfoCriteria example) throws DataAccessException;

    StockInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockInfo record, @Param("example") StockInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockInfo record) throws DataAccessException;

    int updateByPrimaryKey(StockInfo record) throws DataAccessException;
}
