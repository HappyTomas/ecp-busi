package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackDetail;
import com.zengshi.ecp.order.dao.model.OrdBackDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackDetailMapper {
    Long countByExample(OrdBackDetailCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackDetail record) throws DataAccessException;

    int insertSelective(OrdBackDetail record) throws DataAccessException;

    List<OrdBackDetail> selectByExample(OrdBackDetailCriteria example) throws DataAccessException;

    OrdBackDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackDetail record, @Param("example") OrdBackDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackDetail record, @Param("example") OrdBackDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackDetail record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackDetail record) throws DataAccessException;
}