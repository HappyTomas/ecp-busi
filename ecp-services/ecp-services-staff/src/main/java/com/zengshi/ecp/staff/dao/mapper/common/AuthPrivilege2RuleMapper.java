package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthPrivilege2RuleMapper {
    Long countByExample(AuthPrivilege2RuleCriteria example) throws DataAccessException;

    int deleteByExample(AuthPrivilege2RuleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthPrivilege2RuleKey key) throws DataAccessException;

    int insert(AuthPrivilege2Rule record) throws DataAccessException;

    int insertSelective(AuthPrivilege2Rule record) throws DataAccessException;

    List<AuthPrivilege2Rule> selectByExample(AuthPrivilege2RuleCriteria example) throws DataAccessException;

    AuthPrivilege2Rule selectByPrimaryKey(AuthPrivilege2RuleKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthPrivilege2Rule record, @Param("example") AuthPrivilege2RuleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthPrivilege2Rule record, @Param("example") AuthPrivilege2RuleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthPrivilege2Rule record) throws DataAccessException;

    int updateByPrimaryKey(AuthPrivilege2Rule record) throws DataAccessException;
}