package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.ScoreFuncPara;
import com.zengshi.ecp.staff.dao.model.ScoreFuncParaCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreFuncParaMapper {
    long countByExample(ScoreFuncParaCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long paraId) throws DataAccessException;

    int insert(ScoreFuncPara record) throws DataAccessException;

    int insertSelective(ScoreFuncPara record) throws DataAccessException;

    List<ScoreFuncPara> selectByExample(ScoreFuncParaCriteria example) throws DataAccessException;

    ScoreFuncPara selectByPrimaryKey(Long paraId) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreFuncPara record) throws DataAccessException;

    int updateByPrimaryKey(ScoreFuncPara record) throws DataAccessException;

    void insertBatch(List<ScoreFuncPara> recordLst) throws DataAccessException;
}