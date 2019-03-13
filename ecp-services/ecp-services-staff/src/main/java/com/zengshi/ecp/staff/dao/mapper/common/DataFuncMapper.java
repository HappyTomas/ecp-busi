package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.DataFunc;
import com.zengshi.ecp.staff.dao.model.DataFuncCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface DataFuncMapper {
    Long countByExample(DataFuncCriteria example) throws DataAccessException;

    int deleteByExample(DataFuncCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DataFunc record) throws DataAccessException;

    int insertSelective(DataFunc record) throws DataAccessException;

    List<DataFunc> selectByExample(DataFuncCriteria example) throws DataAccessException;

    DataFunc selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") DataFunc record, @Param("example") DataFuncCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") DataFunc record, @Param("example") DataFuncCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(DataFunc record) throws DataAccessException;

    int updateByPrimaryKey(DataFunc record) throws DataAccessException;
}