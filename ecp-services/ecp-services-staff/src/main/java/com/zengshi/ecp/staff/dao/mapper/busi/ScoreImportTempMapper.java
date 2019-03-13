package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreImportTemp;
import com.zengshi.ecp.staff.dao.model.ScoreImportTempCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreImportTempMapper {
    long countByExample(ScoreImportTempCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(ScoreImportTemp record) throws DataAccessException;

    int insertSelective(ScoreImportTemp record) throws DataAccessException;

    List<ScoreImportTemp> selectByExample(ScoreImportTempCriteria example) throws DataAccessException;

    ScoreImportTemp selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreImportTemp record) throws DataAccessException;

    int updateByPrimaryKey(ScoreImportTemp record) throws DataAccessException;

    void insertBatch(List<ScoreImportTemp> recordLst) throws DataAccessException;
}