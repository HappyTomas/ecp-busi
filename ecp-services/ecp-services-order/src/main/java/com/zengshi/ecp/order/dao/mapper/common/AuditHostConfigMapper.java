package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditHostConfig;
import com.zengshi.ecp.order.dao.model.AuditHostConfigCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditHostConfigMapper {
    Long countByExample(AuditHostConfigCriteria example) throws DataAccessException;

    int deleteByExample(AuditHostConfigCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(AuditHostConfig record) throws DataAccessException;

    int insertSelective(AuditHostConfig record) throws DataAccessException;

    List<AuditHostConfig> selectByExample(AuditHostConfigCriteria example) throws DataAccessException;

    AuditHostConfig selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditHostConfig record, @Param("example") AuditHostConfigCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditHostConfig record, @Param("example") AuditHostConfigCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditHostConfig record) throws DataAccessException;

    int updateByPrimaryKey(AuditHostConfig record) throws DataAccessException;
}