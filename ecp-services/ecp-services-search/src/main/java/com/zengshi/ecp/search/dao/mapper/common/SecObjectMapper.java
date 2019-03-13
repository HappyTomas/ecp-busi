package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecObject;
import com.zengshi.ecp.search.dao.model.SecObjectCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecObjectMapper {
    Long countByExample(SecObjectCriteria example) throws DataAccessException;

    int deleteByExample(SecObjectCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecObject record) throws DataAccessException;

    int insertSelective(SecObject record) throws DataAccessException;

    List<SecObject> selectByExample(SecObjectCriteria example) throws DataAccessException;

    SecObject selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecObject record, @Param("example") SecObjectCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecObject record, @Param("example") SecObjectCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecObject record) throws DataAccessException;

    int updateByPrimaryKey(SecObject record) throws DataAccessException;
}