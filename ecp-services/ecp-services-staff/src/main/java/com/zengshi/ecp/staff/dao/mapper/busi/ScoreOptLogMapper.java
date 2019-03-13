package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreOptLog;
import com.zengshi.ecp.staff.dao.model.ScoreOptLogCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreOptLogMapper {
    Long countByExample(ScoreOptLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreOptLog record) throws DataAccessException;

    int insertSelective(ScoreOptLog record) throws DataAccessException;

    List<ScoreOptLog> selectByExample(ScoreOptLogCriteria example) throws DataAccessException;

    ScoreOptLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreOptLog record) throws DataAccessException;

    int updateByPrimaryKey(ScoreOptLog record) throws DataAccessException;
}