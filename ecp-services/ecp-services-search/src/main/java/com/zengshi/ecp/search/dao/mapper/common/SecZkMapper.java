package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecZk;
import com.zengshi.ecp.search.dao.model.SecZkCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecZkMapper {
    Long countByExample(SecZkCriteria example) throws DataAccessException;

    int deleteByExample(SecZkCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecZk record) throws DataAccessException;

    int insertSelective(SecZk record) throws DataAccessException;

    List<SecZk> selectByExample(SecZkCriteria example) throws DataAccessException;

    SecZk selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecZk record, @Param("example") SecZkCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecZk record, @Param("example") SecZkCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecZk record) throws DataAccessException;

    int updateByPrimaryKey(SecZk record) throws DataAccessException;
}