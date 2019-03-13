package com.zengshi.ecp.im.dao.mapper.common;

import com.zengshi.ecp.im.dao.model.ofUser;
import com.zengshi.ecp.im.dao.model.ofUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ofUserMapper {
    Long countByExample(ofUserCriteria example) throws DataAccessException;

    int deleteByExample(ofUserCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String username) throws DataAccessException;

    int insert(ofUser record) throws DataAccessException;

    int insertSelective(ofUser record) throws DataAccessException;

    List<ofUser> selectByExample(ofUserCriteria example) throws DataAccessException;

    ofUser selectByPrimaryKey(String username) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ofUser record, @Param("example") ofUserCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ofUser record, @Param("example") ofUserCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ofUser record) throws DataAccessException;

    int updateByPrimaryKey(ofUser record) throws DataAccessException;
}