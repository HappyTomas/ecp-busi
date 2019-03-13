package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditFileDealLog;
import com.zengshi.ecp.order.dao.model.AuditFileDealLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditFileDealLogMapper {
    Long countByExample(AuditFileDealLogCriteria example) throws DataAccessException;

    int deleteByExample(AuditFileDealLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditFileDealLog record) throws DataAccessException;

    int insertSelective(AuditFileDealLog record) throws DataAccessException;

    List<AuditFileDealLog> selectByExampleWithBLOBs(AuditFileDealLogCriteria example) throws DataAccessException;

    List<AuditFileDealLog> selectByExample(AuditFileDealLogCriteria example) throws DataAccessException;

    AuditFileDealLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditFileDealLog record, @Param("example") AuditFileDealLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") AuditFileDealLog record, @Param("example") AuditFileDealLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditFileDealLog record, @Param("example") AuditFileDealLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditFileDealLog record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(AuditFileDealLog record) throws DataAccessException;

    int updateByPrimaryKey(AuditFileDealLog record) throws DataAccessException;
}