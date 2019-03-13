package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockOptRecord;
import com.zengshi.ecp.goods.dao.model.StockOptRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockOptRecordMapper {
    Long countByExample(StockOptRecordCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockOptRecord record) throws DataAccessException;

    int insertSelective(StockOptRecord record) throws DataAccessException;

    List<StockOptRecord> selectByExample(StockOptRecordCriteria example) throws DataAccessException;

    StockOptRecord selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockOptRecord record, @Param("example") StockOptRecordCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockOptRecord record, @Param("example") StockOptRecordCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockOptRecord record) throws DataAccessException;

    int updateByPrimaryKey(StockOptRecord record) throws DataAccessException;
}
