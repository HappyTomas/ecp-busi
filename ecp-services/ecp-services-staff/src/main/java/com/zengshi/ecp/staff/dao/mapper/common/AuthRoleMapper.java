package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthRoleMapper {
    Long countByExample(AuthRoleCriteria example) throws DataAccessException;

    int deleteByExample(AuthRoleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthRole record) throws DataAccessException;

    int insertSelective(AuthRole record) throws DataAccessException;

    List<AuthRole> selectByExample(AuthRoleCriteria example) throws DataAccessException;

    AuthRole selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthRole record, @Param("example") AuthRoleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthRole record, @Param("example") AuthRoleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthRole record) throws DataAccessException;

    int updateByPrimaryKey(AuthRole record) throws DataAccessException;
}