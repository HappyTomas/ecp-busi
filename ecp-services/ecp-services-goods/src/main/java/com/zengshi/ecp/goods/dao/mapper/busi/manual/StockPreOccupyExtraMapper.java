package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.StockPreOccupy;
import com.zengshi.ecp.goods.dao.model.StockPreOccupyCriteria;

public interface StockPreOccupyExtraMapper {
    public int updatePreOccupyBySend(@Param("record")
    StockPreOccupy stockPreOccupy, @Param("example")
    StockPreOccupyCriteria stockPreOccupyCriteria) throws DataAccessException;
    
    public int insertPreOccupyNotExists(StockPreOccupy stockPreOccupy)throws DataAccessException;
}
