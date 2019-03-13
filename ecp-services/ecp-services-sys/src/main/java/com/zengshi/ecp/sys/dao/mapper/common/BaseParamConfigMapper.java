package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseParamConfig;
import com.zengshi.ecp.sys.dao.model.BaseParamConfigCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseParamConfigMapper {
    Long countByExample(BaseParamConfigCriteria example) throws DataAccessException;

    int deleteByExample(BaseParamConfigCriteria example) throws DataAccessException;
    
    int deleteByPrimaryKey(String paramKey) throws DataAccessException;

    int insert(BaseParamConfig record) throws DataAccessException;

    int insertSelective(BaseParamConfig record) throws DataAccessException;

    List<BaseParamConfig> selectByExample(BaseParamConfigCriteria example) throws DataAccessException;

    BaseParamConfig selectByPrimaryKey(String paramKey) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseParamConfig record, @Param("example") BaseParamConfigCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseParamConfig record, @Param("example") BaseParamConfigCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseParamConfig record) throws DataAccessException;

    int updateByPrimaryKey(BaseParamConfig record) throws DataAccessException;
}
