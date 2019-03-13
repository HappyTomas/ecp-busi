package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthRole2PrivilegeCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRole2PrivilegeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthRole2PrivilegeMapper {
    Long countByExample(AuthRole2PrivilegeCriteria example) throws DataAccessException;

    int deleteByExample(AuthRole2PrivilegeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthRole2PrivilegeKey key) throws DataAccessException;

    int insert(AuthRole2Privilege record) throws DataAccessException;

    int insertSelective(AuthRole2Privilege record) throws DataAccessException;

    List<AuthRole2Privilege> selectByExample(AuthRole2PrivilegeCriteria example) throws DataAccessException;

    AuthRole2Privilege selectByPrimaryKey(AuthRole2PrivilegeKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthRole2Privilege record, @Param("example") AuthRole2PrivilegeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthRole2Privilege record, @Param("example") AuthRole2PrivilegeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthRole2Privilege record) throws DataAccessException;

    int updateByPrimaryKey(AuthRole2Privilege record) throws DataAccessException;
}