package com.zengshi.ecp.demo.dao.mapper.common;

import com.zengshi.ecp.demo.dao.model.Password;
import com.zengshi.ecp.demo.dao.model.PasswordCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface PasswordMapper {
    Long countByExample(PasswordCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String userId) throws DataAccessException;

    int insert(Password record) throws DataAccessException;

    int insertSelective(Password record) throws DataAccessException;

    List<Password> selectByExample(PasswordCriteria example) throws DataAccessException;

    Password selectByPrimaryKey(String userId) throws DataAccessException;

    int updateByPrimaryKeySelective(Password record) throws DataAccessException;

    int updateByPrimaryKey(Password record) throws DataAccessException;
}
