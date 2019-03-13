package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dao.model.ScoreInfoCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreInfoMapper {
    long countByExample(ScoreInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreInfo record) throws DataAccessException;

    int insertSelective(ScoreInfo record) throws DataAccessException;

    List<ScoreInfo> selectByExample(ScoreInfoCriteria example) throws DataAccessException;

    ScoreInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreInfo record) throws DataAccessException;

    int updateByPrimaryKey(ScoreInfo record) throws DataAccessException;

    void insertBatch(List<ScoreInfo> recordLst) throws DataAccessException;
}