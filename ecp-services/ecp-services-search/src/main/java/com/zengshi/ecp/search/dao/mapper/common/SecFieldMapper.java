package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecField;
import com.zengshi.ecp.search.dao.model.SecFieldCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecFieldMapper {
    Long countByExample(SecFieldCriteria example) throws DataAccessException;

    int deleteByExample(SecFieldCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecField record) throws DataAccessException;

    int insertSelective(SecField record) throws DataAccessException;

    List<SecField> selectByExample(SecFieldCriteria example) throws DataAccessException;

    SecField selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecField record, @Param("example") SecFieldCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecField record, @Param("example") SecFieldCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecField record) throws DataAccessException;

    int updateByPrimaryKey(SecField record) throws DataAccessException;
}