package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecConfig2Object;
import com.zengshi.ecp.search.dao.model.SecConfig2ObjectCriteria;
import com.zengshi.ecp.search.dao.model.SecConfig2ObjectKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecConfig2ObjectMapper {
    Long countByExample(SecConfig2ObjectCriteria example) throws DataAccessException;

    int deleteByExample(SecConfig2ObjectCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(SecConfig2ObjectKey key) throws DataAccessException;

    int insert(SecConfig2Object record) throws DataAccessException;

    int insertSelective(SecConfig2Object record) throws DataAccessException;

    List<SecConfig2Object> selectByExample(SecConfig2ObjectCriteria example) throws DataAccessException;

    SecConfig2Object selectByPrimaryKey(SecConfig2ObjectKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecConfig2Object record, @Param("example") SecConfig2ObjectCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecConfig2Object record, @Param("example") SecConfig2ObjectCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecConfig2Object record) throws DataAccessException;

    int updateByPrimaryKey(SecConfig2Object record) throws DataAccessException;
}