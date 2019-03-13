package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseParamSimple;
import com.zengshi.ecp.sys.dao.model.BaseParamSimpleCriteria;
import com.zengshi.ecp.sys.dao.model.BaseParamSimpleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseParamSimpleMapper {
    Long countByExample(BaseParamSimpleCriteria example) throws DataAccessException;
    
    int deleteByExample(BaseParamSimpleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(BaseParamSimpleKey key) throws DataAccessException;

    int insert(BaseParamSimple record) throws DataAccessException;

    int insertSelective(BaseParamSimple record) throws DataAccessException;

    List<BaseParamSimple> selectByExample(BaseParamSimpleCriteria example) throws DataAccessException;

    BaseParamSimple selectByPrimaryKey(BaseParamSimpleKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseParamSimple record, @Param("example") BaseParamSimpleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseParamSimple record, @Param("example") BaseParamSimpleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseParamSimple record) throws DataAccessException;

    int updateByPrimaryKey(BaseParamSimple record) throws DataAccessException;
}
