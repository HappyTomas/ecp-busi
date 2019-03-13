package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfPropValue;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValueCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfPropValueMapper {
    Long countByExample(UnpfPropValueCriteria example) throws DataAccessException;

    int deleteByExample(UnpfPropValueCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfPropValue record) throws DataAccessException;

    int insertSelective(UnpfPropValue record) throws DataAccessException;

    List<UnpfPropValue> selectByExample(UnpfPropValueCriteria example) throws DataAccessException;

    UnpfPropValue selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfPropValue record, @Param("example") UnpfPropValueCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfPropValue record, @Param("example") UnpfPropValueCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfPropValue record) throws DataAccessException;

    int updateByPrimaryKey(UnpfPropValue record) throws DataAccessException;
}