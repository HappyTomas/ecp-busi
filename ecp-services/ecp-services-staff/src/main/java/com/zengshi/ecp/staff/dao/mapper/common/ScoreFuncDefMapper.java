package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.ScoreFuncDef;
import com.zengshi.ecp.staff.dao.model.ScoreFuncDefCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreFuncDefMapper {
    long countByExample(ScoreFuncDefCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long funcId) throws DataAccessException;

    int insert(ScoreFuncDef record) throws DataAccessException;

    int insertSelective(ScoreFuncDef record) throws DataAccessException;

    List<ScoreFuncDef> selectByExample(ScoreFuncDefCriteria example) throws DataAccessException;

    ScoreFuncDef selectByPrimaryKey(Long funcId) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreFuncDef record) throws DataAccessException;

    int updateByPrimaryKey(ScoreFuncDef record) throws DataAccessException;

    void insertBatch(List<ScoreFuncDef> recordLst) throws DataAccessException;
}