package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataAuth;
import com.zengshi.ecp.staff.dao.model.DataAuthCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataAuthMapper {
    Long countByExample(DataAuthCriteria example) throws DataAccessException;

    int deleteByExample(DataAuthCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataAuth record) throws DataAccessException;

    int insertSelective(DataAuth record) throws DataAccessException;

    List<DataAuth> selectByExample(DataAuthCriteria example) throws DataAccessException;

    DataAuth selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataAuth record, @Param("example") DataAuthCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataAuth record, @Param("example") DataAuthCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataAuth record) throws DataAccessException;

    int updateByPrimaryKey(DataAuth record) throws DataAccessException;
}