package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.StockRepAdapt;
import com.zengshi.ecp.goods.dao.model.StockRepAdaptCriteria;
import com.zengshi.ecp.goods.dao.model.StockRepInfoIdxCriteria;

public interface StockRepExtraMapper {

    Long countGroupByGdsId(StockRepInfoIdxCriteria example)throws DataAccessException;

    List<StockRepAdapt> selectGroupByProvince(StockRepAdaptCriteria stockRepAdaptCriteria)throws DataAccessException;

    List<StockRepAdapt> selectGroupByCity(StockRepAdaptCriteria stockRepAdaptCriteria)throws DataAccessException;

}

