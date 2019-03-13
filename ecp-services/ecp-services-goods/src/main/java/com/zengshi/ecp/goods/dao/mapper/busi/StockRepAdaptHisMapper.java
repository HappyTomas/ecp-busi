package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockRepAdaptHis;
import com.zengshi.ecp.goods.dao.model.StockRepAdaptHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockRepAdaptHisMapper {
    Long countByExample(StockRepAdaptHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockRepAdaptHis record) throws DataAccessException;

    int insertSelective(StockRepAdaptHis record) throws DataAccessException;

    List<StockRepAdaptHis> selectByExample(StockRepAdaptHisCriteria example) throws DataAccessException;

    StockRepAdaptHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockRepAdaptHis record, @Param("example") StockRepAdaptHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockRepAdaptHis record, @Param("example") StockRepAdaptHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockRepAdaptHis record) throws DataAccessException;

    int updateByPrimaryKey(StockRepAdaptHis record) throws DataAccessException;
}
