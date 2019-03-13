package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecOperLog;
import com.zengshi.ecp.search.dao.model.SecOperLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecOperLogMapper {
    Long countByExample(SecOperLogCriteria example) throws DataAccessException;

    int deleteByExample(SecOperLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecOperLog record) throws DataAccessException;

    int insertSelective(SecOperLog record) throws DataAccessException;

    List<SecOperLog> selectByExample(SecOperLogCriteria example) throws DataAccessException;

    SecOperLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecOperLog record, @Param("example") SecOperLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecOperLog record, @Param("example") SecOperLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecOperLog record) throws DataAccessException;

    int updateByPrimaryKey(SecOperLog record) throws DataAccessException;
}