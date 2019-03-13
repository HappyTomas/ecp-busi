package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthMenu;
import com.zengshi.ecp.staff.dao.model.AuthMenuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthMenuMapper {
    Long countByExample(AuthMenuCriteria example) throws DataAccessException;

    int deleteByExample(AuthMenuCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthMenu record) throws DataAccessException;

    int insertSelective(AuthMenu record) throws DataAccessException;

    List<AuthMenu> selectByExample(AuthMenuCriteria example) throws DataAccessException;

    AuthMenu selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthMenu record, @Param("example") AuthMenuCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthMenu record, @Param("example") AuthMenuCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthMenu record) throws DataAccessException;

    int updateByPrimaryKey(AuthMenu record) throws DataAccessException;
}