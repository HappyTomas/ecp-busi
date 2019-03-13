package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockRepInfoIdx;
import com.zengshi.ecp.goods.dao.model.StockRepInfoIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockRepInfoIdxMapper {
    Long countByExample(StockRepInfoIdxCriteria example) throws DataAccessException;

    int insert(StockRepInfoIdx record) throws DataAccessException;

    int insertSelective(StockRepInfoIdx record) throws DataAccessException;

    List<StockRepInfoIdx> selectByExample(StockRepInfoIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockRepInfoIdx record, @Param("example") StockRepInfoIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockRepInfoIdx record, @Param("example") StockRepInfoIdxCriteria example) throws DataAccessException;
}
