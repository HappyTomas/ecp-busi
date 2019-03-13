package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecObjectProcessor;
import com.zengshi.ecp.search.dao.model.SecObjectProcessorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecObjectProcessorMapper {
    Long countByExample(SecObjectProcessorCriteria example) throws DataAccessException;

    int deleteByExample(SecObjectProcessorCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String processorName) throws DataAccessException;

    int insert(SecObjectProcessor record) throws DataAccessException;

    int insertSelective(SecObjectProcessor record) throws DataAccessException;

    List<SecObjectProcessor> selectByExample(SecObjectProcessorCriteria example) throws DataAccessException;

    SecObjectProcessor selectByPrimaryKey(String processorName) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecObjectProcessor record, @Param("example") SecObjectProcessorCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecObjectProcessor record, @Param("example") SecObjectProcessorCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecObjectProcessor record) throws DataAccessException;

    int updateByPrimaryKey(SecObjectProcessor record) throws DataAccessException;
}