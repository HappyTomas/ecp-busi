package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseParamDynamic;
import com.zengshi.ecp.sys.dao.model.BaseParamDynamicCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseParamDynamicMapper {
    Long countByExample(BaseParamDynamicCriteria example) throws DataAccessException;

    int deleteByExample(BaseParamDynamicCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String paramLinkKey) throws DataAccessException;

    int insert(BaseParamDynamic record) throws DataAccessException;

    int insertSelective(BaseParamDynamic record) throws DataAccessException;

    List<BaseParamDynamic> selectByExample(BaseParamDynamicCriteria example) throws DataAccessException;

    BaseParamDynamic selectByPrimaryKey(String paramLinkKey) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseParamDynamic record, @Param("example") BaseParamDynamicCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseParamDynamic record, @Param("example") BaseParamDynamicCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseParamDynamic record) throws DataAccessException;

    int updateByPrimaryKey(BaseParamDynamic record) throws DataAccessException;
}
