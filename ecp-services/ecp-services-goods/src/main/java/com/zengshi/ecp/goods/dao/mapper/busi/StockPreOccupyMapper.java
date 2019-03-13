package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockPreOccupy;
import com.zengshi.ecp.goods.dao.model.StockPreOccupyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockPreOccupyMapper {
    Long countByExample(StockPreOccupyCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockPreOccupy record) throws DataAccessException;

    int insertSelective(StockPreOccupy record) throws DataAccessException;

    List<StockPreOccupy> selectByExample(StockPreOccupyCriteria example) throws DataAccessException;

    StockPreOccupy selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockPreOccupy record, @Param("example") StockPreOccupyCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockPreOccupy record, @Param("example") StockPreOccupyCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockPreOccupy record) throws DataAccessException;

    int updateByPrimaryKey(StockPreOccupy record) throws DataAccessException;
}
