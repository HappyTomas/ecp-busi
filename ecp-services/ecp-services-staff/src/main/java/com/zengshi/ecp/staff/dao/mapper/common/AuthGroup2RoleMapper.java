package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthGroup2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthGroup2RoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthGroup2RoleMapper {
    Long countByExample(AuthGroup2RoleCriteria example) throws DataAccessException;

    int deleteByExample(AuthGroup2RoleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthGroup2RoleKey key) throws DataAccessException;

    int insert(AuthGroup2Role record) throws DataAccessException;

    int insertSelective(AuthGroup2Role record) throws DataAccessException;

    List<AuthGroup2Role> selectByExample(AuthGroup2RoleCriteria example) throws DataAccessException;

    AuthGroup2Role selectByPrimaryKey(AuthGroup2RoleKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthGroup2Role record, @Param("example") AuthGroup2RoleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthGroup2Role record, @Param("example") AuthGroup2RoleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthGroup2Role record) throws DataAccessException;

    int updateByPrimaryKey(AuthGroup2Role record) throws DataAccessException;
}