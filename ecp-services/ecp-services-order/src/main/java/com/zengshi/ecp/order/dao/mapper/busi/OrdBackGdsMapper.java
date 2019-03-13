package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackGds;
import com.zengshi.ecp.order.dao.model.OrdBackGdsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackGdsMapper {
    Long countByExample(OrdBackGdsCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackGdsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackGds record) throws DataAccessException;

    int insertSelective(OrdBackGds record) throws DataAccessException;

    List<OrdBackGds> selectByExample(OrdBackGdsCriteria example) throws DataAccessException;

    OrdBackGds selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackGds record, @Param("example") OrdBackGdsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackGds record, @Param("example") OrdBackGdsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackGds record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackGds record) throws DataAccessException;
}