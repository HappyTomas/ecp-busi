package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.ScoreType;
import com.zengshi.ecp.staff.dao.model.ScoreTypeCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreTypeMapper {
    long countByExample(ScoreTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String scoreType) throws DataAccessException;

    int insert(ScoreType record) throws DataAccessException;

    int insertSelective(ScoreType record) throws DataAccessException;

    List<ScoreType> selectByExample(ScoreTypeCriteria example) throws DataAccessException;

    ScoreType selectByPrimaryKey(String scoreType) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreType record) throws DataAccessException;

    int updateByPrimaryKey(ScoreType record) throws DataAccessException;

    void insertBatch(List<ScoreType> recordLst) throws DataAccessException;
}