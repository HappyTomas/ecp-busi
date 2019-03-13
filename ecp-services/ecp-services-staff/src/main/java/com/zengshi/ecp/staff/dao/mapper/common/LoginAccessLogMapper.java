package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.LoginAccessLog;
import com.zengshi.ecp.staff.dao.model.LoginAccessLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface LoginAccessLogMapper {
    Long countByExample(LoginAccessLogCriteria example) throws DataAccessException;

    int deleteByExample(LoginAccessLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(LoginAccessLog record) throws DataAccessException;

    int insertSelective(LoginAccessLog record) throws DataAccessException;

    List<LoginAccessLog> selectByExample(LoginAccessLogCriteria example) throws DataAccessException;

    LoginAccessLog selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") LoginAccessLog record, @Param("example") LoginAccessLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") LoginAccessLog record, @Param("example") LoginAccessLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(LoginAccessLog record) throws DataAccessException;

    int updateByPrimaryKey(LoginAccessLog record) throws DataAccessException;
}