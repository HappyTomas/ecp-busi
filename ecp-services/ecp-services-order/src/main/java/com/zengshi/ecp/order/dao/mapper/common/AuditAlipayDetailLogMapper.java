package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditAlipayDetailLog;
import com.zengshi.ecp.order.dao.model.AuditAlipayDetailLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditAlipayDetailLogMapper {
    Long countByExample(AuditAlipayDetailLogCriteria example) throws DataAccessException;

    int deleteByExample(AuditAlipayDetailLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditAlipayDetailLog record) throws DataAccessException;

    int insertSelective(AuditAlipayDetailLog record) throws DataAccessException;

    List<AuditAlipayDetailLog> selectByExample(AuditAlipayDetailLogCriteria example) throws DataAccessException;

    AuditAlipayDetailLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditAlipayDetailLog record, @Param("example") AuditAlipayDetailLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditAlipayDetailLog record, @Param("example") AuditAlipayDetailLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditAlipayDetailLog record) throws DataAccessException;

    int updateByPrimaryKey(AuditAlipayDetailLog record) throws DataAccessException;
}