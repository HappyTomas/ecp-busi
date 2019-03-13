package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockInDetail;
import com.zengshi.ecp.goods.dao.model.StockInDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockInDetailMapper {
    Long countByExample(StockInDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockInDetail record) throws DataAccessException;

    int insertSelective(StockInDetail record) throws DataAccessException;

    List<StockInDetail> selectByExample(StockInDetailCriteria example) throws DataAccessException;

    StockInDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockInDetail record, @Param("example") StockInDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockInDetail record, @Param("example") StockInDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockInDetail record) throws DataAccessException;

    int updateByPrimaryKey(StockInDetail record) throws DataAccessException;
}
