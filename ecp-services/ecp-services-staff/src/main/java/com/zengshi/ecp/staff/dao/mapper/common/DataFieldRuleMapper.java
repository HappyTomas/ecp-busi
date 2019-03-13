package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataFieldRule;
import com.zengshi.ecp.staff.dao.model.DataFieldRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataFieldRuleMapper {
    Long countByExample(DataFieldRuleCriteria example) throws DataAccessException;

    int deleteByExample(DataFieldRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataFieldRule record) throws DataAccessException;

    int insertSelective(DataFieldRule record) throws DataAccessException;

    List<DataFieldRule> selectByExample(DataFieldRuleCriteria example) throws DataAccessException;

    DataFieldRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataFieldRule record, @Param("example") DataFieldRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataFieldRule record, @Param("example") DataFieldRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataFieldRule record) throws DataAccessException;

    int updateByPrimaryKey(DataFieldRule record) throws DataAccessException;
}