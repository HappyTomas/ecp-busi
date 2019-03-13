package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditAutoLog;
import com.zengshi.ecp.order.dao.model.AuditAutoLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditAutoLogMapper {
    Long countByExample(AuditAutoLogCriteria example) throws DataAccessException;

    int deleteByExample(AuditAutoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditAutoLog record) throws DataAccessException;

    int insertSelective(AuditAutoLog record) throws DataAccessException;

    List<AuditAutoLog> selectByExampleWithBLOBs(AuditAutoLogCriteria example) throws DataAccessException;

    List<AuditAutoLog> selectByExample(AuditAutoLogCriteria example) throws DataAccessException;

    AuditAutoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditAutoLog record, @Param("example") AuditAutoLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") AuditAutoLog record, @Param("example") AuditAutoLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditAutoLog record, @Param("example") AuditAutoLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditAutoLog record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(AuditAutoLog record) throws DataAccessException;

    int updateByPrimaryKey(AuditAutoLog record) throws DataAccessException;
}