package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecObjectBuildLog;
import com.zengshi.ecp.search.dao.model.SecObjectBuildLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecObjectBuildLogMapper {
    Long countByExample(SecObjectBuildLogCriteria example) throws DataAccessException;

    int deleteByExample(SecObjectBuildLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecObjectBuildLog record) throws DataAccessException;

    int insertSelective(SecObjectBuildLog record) throws DataAccessException;

    List<SecObjectBuildLog> selectByExample(SecObjectBuildLogCriteria example) throws DataAccessException;

    SecObjectBuildLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecObjectBuildLog record, @Param("example") SecObjectBuildLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecObjectBuildLog record, @Param("example") SecObjectBuildLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecObjectBuildLog record) throws DataAccessException;

    int updateByPrimaryKey(SecObjectBuildLog record) throws DataAccessException;
}