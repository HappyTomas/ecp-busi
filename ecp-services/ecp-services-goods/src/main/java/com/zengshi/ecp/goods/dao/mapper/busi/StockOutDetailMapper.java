package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.StockOutDetail;
import com.zengshi.ecp.goods.dao.model.StockOutDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface StockOutDetailMapper {
    Long countByExample(StockOutDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(StockOutDetail record) throws DataAccessException;

    int insertSelective(StockOutDetail record) throws DataAccessException;

    List<StockOutDetail> selectByExample(StockOutDetailCriteria example) throws DataAccessException;

    StockOutDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") StockOutDetail record, @Param("example") StockOutDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") StockOutDetail record, @Param("example") StockOutDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(StockOutDetail record) throws DataAccessException;

    int updateByPrimaryKey(StockOutDetail record) throws DataAccessException;
}
