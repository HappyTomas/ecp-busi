package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecArgs;
import com.zengshi.ecp.search.dao.model.SecArgsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecArgsMapper {
    Long countByExample(SecArgsCriteria example) throws DataAccessException;

    int deleteByExample(SecArgsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String argsKey) throws DataAccessException;

    int insert(SecArgs record) throws DataAccessException;

    int insertSelective(SecArgs record) throws DataAccessException;

    List<SecArgs> selectByExample(SecArgsCriteria example) throws DataAccessException;

    SecArgs selectByPrimaryKey(String argsKey) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecArgs record, @Param("example") SecArgsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecArgs record, @Param("example") SecArgsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecArgs record) throws DataAccessException;

    int updateByPrimaryKey(SecArgs record) throws DataAccessException;
}