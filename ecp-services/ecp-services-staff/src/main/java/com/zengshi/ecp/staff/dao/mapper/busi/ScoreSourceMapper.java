package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreSource;
import com.zengshi.ecp.staff.dao.model.ScoreSourceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ScoreSourceMapper {
    Long countByExample(ScoreSourceCriteria example) throws DataAccessException;

    int deleteByExample(ScoreSourceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreSource record) throws DataAccessException;

    int insertSelective(ScoreSource record) throws DataAccessException;

    List<ScoreSource> selectByExample(ScoreSourceCriteria example) throws DataAccessException;

    ScoreSource selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ScoreSource record, @Param("example") ScoreSourceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ScoreSource record, @Param("example") ScoreSourceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreSource record) throws DataAccessException;

    int updateByPrimaryKey(ScoreSource record) throws DataAccessException;
    
    int updateByCreateTimeEndSelective(ScoreSource record) throws DataAccessException;
}