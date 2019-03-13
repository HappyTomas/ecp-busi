package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.ScoreAction;
import com.zengshi.ecp.staff.dao.model.ScoreActionCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreActionMapper {
    long countByExample(ScoreActionCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String actionType) throws DataAccessException;

    int insert(ScoreAction record) throws DataAccessException;

    int insertSelective(ScoreAction record) throws DataAccessException;

    List<ScoreAction> selectByExample(ScoreActionCriteria example) throws DataAccessException;

    ScoreAction selectByPrimaryKey(String actionType) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreAction record) throws DataAccessException;

    int updateByPrimaryKey(ScoreAction record) throws DataAccessException;

    void insertBatch(List<ScoreAction> recordLst) throws DataAccessException;
}