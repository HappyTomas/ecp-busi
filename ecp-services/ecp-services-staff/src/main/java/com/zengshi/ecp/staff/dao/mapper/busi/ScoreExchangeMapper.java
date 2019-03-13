package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreExchange;
import com.zengshi.ecp.staff.dao.model.ScoreExchangeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ScoreExchangeMapper {
    Long countByExample(ScoreExchangeCriteria example) throws DataAccessException;

    int deleteByExample(ScoreExchangeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreExchange record) throws DataAccessException;

    int insertSelective(ScoreExchange record) throws DataAccessException;

    List<ScoreExchange> selectByExample(ScoreExchangeCriteria example) throws DataAccessException;

    ScoreExchange selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ScoreExchange record, @Param("example") ScoreExchangeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ScoreExchange record, @Param("example") ScoreExchangeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreExchange record) throws DataAccessException;

    int updateByPrimaryKey(ScoreExchange record) throws DataAccessException;
}