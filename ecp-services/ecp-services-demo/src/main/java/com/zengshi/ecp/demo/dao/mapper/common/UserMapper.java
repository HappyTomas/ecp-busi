package com.zengshi.ecp.demo.dao.mapper.common;

import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.dao.model.UserCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface UserMapper {
    int countByExample(UserCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String userId) throws DataAccessException;

    int insert(User record) throws DataAccessException;

    int insertSelective(User record) throws DataAccessException;

    List<User> selectByExample(UserCriteria example) throws DataAccessException;

    User selectByPrimaryKey(String userId) throws DataAccessException;

    int updateByPrimaryKeySelective(User record) throws DataAccessException;

    int updateByPrimaryKey(User record) throws DataAccessException;
}
