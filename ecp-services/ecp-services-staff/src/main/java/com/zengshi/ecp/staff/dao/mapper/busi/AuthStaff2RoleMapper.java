package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthStaff2RoleMapper {
    Long countByExample(AuthStaff2RoleCriteria example) throws DataAccessException;

    int deleteByExample(AuthStaff2RoleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthStaff2RoleKey key) throws DataAccessException;

    int insert(AuthStaff2Role record) throws DataAccessException;

    int insertSelective(AuthStaff2Role record) throws DataAccessException;

    List<AuthStaff2Role> selectByExample(AuthStaff2RoleCriteria example) throws DataAccessException;

    AuthStaff2Role selectByPrimaryKey(AuthStaff2RoleKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthStaff2Role record, @Param("example") AuthStaff2RoleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthStaff2Role record, @Param("example") AuthStaff2RoleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaff2Role record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaff2Role record) throws DataAccessException;
}