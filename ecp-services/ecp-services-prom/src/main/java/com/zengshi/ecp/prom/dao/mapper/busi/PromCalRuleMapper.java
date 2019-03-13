package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromCalRule;
import com.zengshi.ecp.prom.dao.model.PromCalRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromCalRuleMapper {
    Long countByExample(PromCalRuleCriteria example) throws DataAccessException;

    int deleteByExample(PromCalRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromCalRule record) throws DataAccessException;

    int insertSelective(PromCalRule record) throws DataAccessException;

    List<PromCalRule> selectByExampleWithBLOBs(PromCalRuleCriteria example) throws DataAccessException;

    List<PromCalRule> selectByExample(PromCalRuleCriteria example) throws DataAccessException;

    PromCalRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromCalRule record, @Param("example") PromCalRuleCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") PromCalRule record, @Param("example") PromCalRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromCalRule record, @Param("example") PromCalRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromCalRule record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(PromCalRule record) throws DataAccessException;

    int updateByPrimaryKey(PromCalRule record) throws DataAccessException;
}