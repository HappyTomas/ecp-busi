package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataRule;
import com.zengshi.ecp.staff.dao.model.DataRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataRuleMapper {
    Long countByExample(DataRuleCriteria example) throws DataAccessException;

    int deleteByExample(DataRuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataRule record) throws DataAccessException;

    int insertSelective(DataRule record) throws DataAccessException;

    List<DataRule> selectByExample(DataRuleCriteria example) throws DataAccessException;

    DataRule selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataRule record, @Param("example") DataRuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataRule record, @Param("example") DataRuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataRule record) throws DataAccessException;

    int updateByPrimaryKey(DataRule record) throws DataAccessException;
}