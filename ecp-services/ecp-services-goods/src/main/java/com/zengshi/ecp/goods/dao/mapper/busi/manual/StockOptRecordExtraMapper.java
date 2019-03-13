package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.StockOptRecord;

public interface StockOptRecordExtraMapper {

   public int insertStockOptRecordNotExists(StockOptRecord optRecord)throws DataAccessException;

    
}

