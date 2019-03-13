package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecRedirect;
import com.zengshi.ecp.search.dao.model.SecRedirectCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecRedirectMapper {
    Long countByExample(SecRedirectCriteria example) throws DataAccessException;

    int deleteByExample(SecRedirectCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecRedirect record) throws DataAccessException;

    int insertSelective(SecRedirect record) throws DataAccessException;

    List<SecRedirect> selectByExample(SecRedirectCriteria example) throws DataAccessException;

    SecRedirect selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecRedirect record, @Param("example") SecRedirectCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecRedirect record, @Param("example") SecRedirectCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecRedirect record) throws DataAccessException;

    int updateByPrimaryKey(SecRedirect record) throws DataAccessException;
}