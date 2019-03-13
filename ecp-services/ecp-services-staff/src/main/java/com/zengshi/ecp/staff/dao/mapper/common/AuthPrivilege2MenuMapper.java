package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2MenuCriteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2MenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthPrivilege2MenuMapper {
    Long countByExample(AuthPrivilege2MenuCriteria example) throws DataAccessException;

    int deleteByExample(AuthPrivilege2MenuCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthPrivilege2MenuKey key) throws DataAccessException;

    int insert(AuthPrivilege2Menu record) throws DataAccessException;

    int insertSelective(AuthPrivilege2Menu record) throws DataAccessException;

    List<AuthPrivilege2Menu> selectByExample(AuthPrivilege2MenuCriteria example) throws DataAccessException;

    AuthPrivilege2Menu selectByPrimaryKey(AuthPrivilege2MenuKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthPrivilege2Menu record, @Param("example") AuthPrivilege2MenuCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthPrivilege2Menu record, @Param("example") AuthPrivilege2MenuCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthPrivilege2Menu record) throws DataAccessException;

    int updateByPrimaryKey(AuthPrivilege2Menu record) throws DataAccessException;
}