package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockSkuEntityHis;
import com.zengshi.ecp.goods.dao.model.StockSkuEntityHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockSkuEntityHisMapper {
    Long countByExample(StockSkuEntityHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockSkuEntityHis record) throws DataAccessException;

    int insertSelective(StockSkuEntityHis record) throws DataAccessException;

    List<StockSkuEntityHis> selectByExample(StockSkuEntityHisCriteria example) throws DataAccessException;

    StockSkuEntityHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockSkuEntityHis record, @Param("example") StockSkuEntityHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockSkuEntityHis record, @Param("example") StockSkuEntityHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockSkuEntityHis record) throws DataAccessException;

    int updateByPrimaryKey(StockSkuEntityHis record) throws DataAccessException;
}
