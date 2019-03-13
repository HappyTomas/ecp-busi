package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecFieldProcessor;
import com.zengshi.ecp.search.dao.model.SecFieldProcessorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecFieldProcessorMapper {
    Long countByExample(SecFieldProcessorCriteria example) throws DataAccessException;

    int deleteByExample(SecFieldProcessorCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String processorName) throws DataAccessException;

    int insert(SecFieldProcessor record) throws DataAccessException;

    int insertSelective(SecFieldProcessor record) throws DataAccessException;

    List<SecFieldProcessor> selectByExample(SecFieldProcessorCriteria example) throws DataAccessException;

    SecFieldProcessor selectByPrimaryKey(String processorName) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecFieldProcessor record, @Param("example") SecFieldProcessorCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecFieldProcessor record, @Param("example") SecFieldProcessorCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecFieldProcessor record) throws DataAccessException;

    int updateByPrimaryKey(SecFieldProcessor record) throws DataAccessException;
}