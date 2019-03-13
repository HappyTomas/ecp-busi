package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dao.model.SecConfigCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecConfigMapper {
    Long countByExample(SecConfigCriteria example) throws DataAccessException;

    int deleteByExample(SecConfigCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecConfig record) throws DataAccessException;

    int insertSelective(SecConfig record) throws DataAccessException;

    List<SecConfig> selectByExample(SecConfigCriteria example) throws DataAccessException;

    SecConfig selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecConfig record, @Param("example") SecConfigCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecConfig record, @Param("example") SecConfigCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecConfig record) throws DataAccessException;

    int updateByPrimaryKey(SecConfig record) throws DataAccessException;
}