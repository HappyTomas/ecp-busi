package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dao.model.CustLevelRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustLevelRuleMapper {
    Long countByExample(CustLevelRuleCriteria example) throws DataAccessException;

    int deleteByExample(CustLevelRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String custLevelCode) throws DataAccessException;

    int insert(CustLevelRule record) throws DataAccessException;

    int insertSelective(CustLevelRule record) throws DataAccessException;

    List<CustLevelRule> selectByExample(CustLevelRuleCriteria example) throws DataAccessException;

    CustLevelRule selectByPrimaryKey(String custLevelCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustLevelRule record, @Param("example") CustLevelRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustLevelRule record, @Param("example") CustLevelRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustLevelRule record) throws DataAccessException;

    int updateByPrimaryKey(CustLevelRule record) throws DataAccessException;
}