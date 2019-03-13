package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecHotKeyword;
import com.zengshi.ecp.search.dao.model.SecHotKeywordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecHotKeywordMapper {
    Long countByExample(SecHotKeywordCriteria example) throws DataAccessException;

    int deleteByExample(SecHotKeywordCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecHotKeyword record) throws DataAccessException;

    int insertSelective(SecHotKeyword record) throws DataAccessException;

    List<SecHotKeyword> selectByExample(SecHotKeywordCriteria example) throws DataAccessException;

    SecHotKeyword selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecHotKeyword record, @Param("example") SecHotKeywordCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecHotKeyword record, @Param("example") SecHotKeywordCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecHotKeyword record) throws DataAccessException;

    int updateByPrimaryKey(SecHotKeyword record) throws DataAccessException;
}