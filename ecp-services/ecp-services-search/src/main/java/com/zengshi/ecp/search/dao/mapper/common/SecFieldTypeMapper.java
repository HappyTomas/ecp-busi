package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecFieldType;
import com.zengshi.ecp.search.dao.model.SecFieldTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecFieldTypeMapper {
    Long countByExample(SecFieldTypeCriteria example) throws DataAccessException;

    int deleteByExample(SecFieldTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String typeName) throws DataAccessException;

    int insert(SecFieldType record) throws DataAccessException;

    int insertSelective(SecFieldType record) throws DataAccessException;

    List<SecFieldType> selectByExample(SecFieldTypeCriteria example) throws DataAccessException;

    SecFieldType selectByPrimaryKey(String typeName) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecFieldType record, @Param("example") SecFieldTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecFieldType record, @Param("example") SecFieldTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecFieldType record) throws DataAccessException;

    int updateByPrimaryKey(SecFieldType record) throws DataAccessException;
}