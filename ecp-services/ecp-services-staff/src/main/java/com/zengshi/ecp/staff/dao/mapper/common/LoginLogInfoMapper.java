package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.LoginLogInfo;
import com.zengshi.ecp.staff.dao.model.LoginLogInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface LoginLogInfoMapper {
    Long countByExample(LoginLogInfoCriteria example) throws DataAccessException;

    int deleteByExample(LoginLogInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(LoginLogInfo record) throws DataAccessException;

    int insertSelective(LoginLogInfo record) throws DataAccessException;

    List<LoginLogInfo> selectByExample(LoginLogInfoCriteria example) throws DataAccessException;

    LoginLogInfo selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") LoginLogInfo record, @Param("example") LoginLogInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") LoginLogInfo record, @Param("example") LoginLogInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(LoginLogInfo record) throws DataAccessException;

    int updateByPrimaryKey(LoginLogInfo record) throws DataAccessException;
}