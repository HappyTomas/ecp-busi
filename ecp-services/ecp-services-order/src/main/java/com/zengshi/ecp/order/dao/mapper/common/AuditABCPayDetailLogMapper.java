package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditABCPayDetailLog;
import com.zengshi.ecp.order.dao.model.AuditABCPayDetailLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditABCPayDetailLogMapper {
    Long countByExample(AuditABCPayDetailLogCriteria example) throws DataAccessException;

    int deleteByExample(AuditABCPayDetailLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditABCPayDetailLog record) throws DataAccessException;

    int insertSelective(AuditABCPayDetailLog record) throws DataAccessException;

    List<AuditABCPayDetailLog> selectByExample(AuditABCPayDetailLogCriteria example) throws DataAccessException;

    AuditABCPayDetailLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditABCPayDetailLog record, @Param("example") AuditABCPayDetailLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditABCPayDetailLog record, @Param("example") AuditABCPayDetailLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditABCPayDetailLog record) throws DataAccessException;

    int updateByPrimaryKey(AuditABCPayDetailLog record) throws DataAccessException;
}