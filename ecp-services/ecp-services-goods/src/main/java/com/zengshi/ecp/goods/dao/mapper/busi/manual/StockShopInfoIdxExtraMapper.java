package com.zengshi.ecp.goods.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.StockShopInfoIdxCriteria;

public interface StockShopInfoIdxExtraMapper {

    public Long countGroupBySkuId(StockShopInfoIdxCriteria shopInfoIdxCriteria)throws DataAccessException;

}

