package com.zengshi.ecp.unpf.dao.mapper.common;

import com.zengshi.ecp.unpf.dao.model.UnpfProp;
import com.zengshi.ecp.unpf.dao.model.UnpfPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfPropMapper {
    Long countByExample(UnpfPropCriteria example) throws DataAccessException;

    int deleteByExample(UnpfPropCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfProp record) throws DataAccessException;

    int insertSelective(UnpfProp record) throws DataAccessException;

    List<UnpfProp> selectByExample(UnpfPropCriteria example) throws DataAccessException;

    UnpfProp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfProp record, @Param("example") UnpfPropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfProp record, @Param("example") UnpfPropCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfProp record) throws DataAccessException;

    int updateByPrimaryKey(UnpfProp record) throws DataAccessException;
}