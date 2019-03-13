package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.ScoreConsumeRule;
import com.zengshi.ecp.staff.dao.model.ScoreConsumeRuleCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface ScoreConsumeRuleMapper {
    Long countByExample(ScoreConsumeRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ScoreConsumeRule record) throws DataAccessException;

    int insertSelective(ScoreConsumeRule record) throws DataAccessException;

    List<ScoreConsumeRule> selectByExample(ScoreConsumeRuleCriteria example) throws DataAccessException;

    ScoreConsumeRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(ScoreConsumeRule record) throws DataAccessException;

    int updateByPrimaryKey(ScoreConsumeRule record) throws DataAccessException;
}