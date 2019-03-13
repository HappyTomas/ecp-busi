package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockShopInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockShopInfoIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockShopInfoIdxMapper {
    Long countByExample(StockShopInfoIdxCriteria example) throws DataAccessException;

    int deleteByExample(StockShopInfoIdxCriteria example) throws DataAccessException;

    int insert(StockShopInfoIdx record) throws DataAccessException;

    int insertSelective(StockShopInfoIdx record) throws DataAccessException;

    List<StockShopInfoIdx> selectByExample(StockShopInfoIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockShopInfoIdx record, @Param("example") StockShopInfoIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockShopInfoIdx record, @Param("example") StockShopInfoIdxCriteria example) throws DataAccessException;
}
