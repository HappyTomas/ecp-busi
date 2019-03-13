package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.dao.model.AuthPrivilegeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthPrivilegeMapper {
    Long countByExample(AuthPrivilegeCriteria example) throws DataAccessException;

    int deleteByExample(AuthPrivilegeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthPrivilege record) throws DataAccessException;

    int insertSelective(AuthPrivilege record) throws DataAccessException;

    List<AuthPrivilege> selectByExample(AuthPrivilegeCriteria example) throws DataAccessException;

    AuthPrivilege selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthPrivilege record, @Param("example") AuthPrivilegeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthPrivilege record, @Param("example") AuthPrivilegeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthPrivilege record) throws DataAccessException;

    int updateByPrimaryKey(AuthPrivilege record) throws DataAccessException;
}