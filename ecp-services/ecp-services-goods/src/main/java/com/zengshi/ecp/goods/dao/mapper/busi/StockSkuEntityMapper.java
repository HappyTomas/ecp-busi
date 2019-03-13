package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockSkuEntity;
import com.zengshi.ecp.goods.dao.model.StockSkuEntityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockSkuEntityMapper {
    Long countByExample(StockSkuEntityCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockSkuEntity record) throws DataAccessException;

    int insertSelective(StockSkuEntity record) throws DataAccessException;

    List<StockSkuEntity> selectByExample(StockSkuEntityCriteria example) throws DataAccessException;

    StockSkuEntity selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockSkuEntity record, @Param("example") StockSkuEntityCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockSkuEntity record, @Param("example") StockSkuEntityCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockSkuEntity record) throws DataAccessException;

    int updateByPrimaryKey(StockSkuEntity record) throws DataAccessException;
}
