package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreSignCheck;
import com.zengshi.ecp.staff.dao.model.ScoreSignCheckCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ScoreSignCheckMapper {
    Long countByExample(ScoreSignCheckCriteria example) throws DataAccessException;

    int deleteByExample(ScoreSignCheckCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long staffId) throws DataAccessException;

    int insert(ScoreSignCheck record) throws DataAccessException;

    int insertSelective(ScoreSignCheck record) throws DataAccessException;

    List<ScoreSignCheck> selectByExample(ScoreSignCheckCriteria example) throws DataAccessException;

    ScoreSignCheck selectByPrimaryKey(Long staffId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ScoreSignCheck record, @Param("example") ScoreSignCheckCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ScoreSignCheck record, @Param("example") ScoreSignCheckCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreSignCheck record) throws DataAccessException;

    int updateByPrimaryKey(ScoreSignCheck record) throws DataAccessException;
}