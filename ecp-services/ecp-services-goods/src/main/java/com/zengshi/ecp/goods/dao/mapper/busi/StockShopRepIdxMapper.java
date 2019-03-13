package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockShopRepIdx;
import com.zengshi.ecp.goods.dao.model.StockShopRepIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockShopRepIdxMapper {
    Long countByExample(StockShopRepIdxCriteria example) throws DataAccessException;

    int insert(StockShopRepIdx record) throws DataAccessException;

    int insertSelective(StockShopRepIdx record) throws DataAccessException;

    List<StockShopRepIdx> selectByExample(StockShopRepIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockShopRepIdx record, @Param("example") StockShopRepIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockShopRepIdx record, @Param("example") StockShopRepIdxCriteria example) throws DataAccessException;
}
