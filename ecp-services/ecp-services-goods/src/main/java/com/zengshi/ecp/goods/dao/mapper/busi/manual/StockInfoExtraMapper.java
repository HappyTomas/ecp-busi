package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.StockInfoCriteria;

public interface StockInfoExtraMapper {

    
    public int updateStockInfoTurnUp(@Param("count")Long count,@Param("staffId")Long staffId,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

    public int updateStockInfoTurnDown(@Param("count")Long count,@Param("staffId")Long staffId,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

    public int updateStockInfoTurnSend(@Param("count")Long count,@Param("staffId")Long staffId,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

    public int updateStockInfoTurnCancelPre(@Param("count")Long count,@Param("staffId")Long staffId,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

    public int updateStockInfoTurnAddPre(@Param("count")Long count,@Param("staffId")Long staffId,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

    public int updateStockInfoTurnByErp(@Param("count")Long count,@Param("example")StockInfoCriteria stockInfoCriteria)throws DataAccessException;

}

