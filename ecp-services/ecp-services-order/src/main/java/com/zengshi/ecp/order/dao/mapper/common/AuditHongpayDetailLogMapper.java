package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditHongpayDetailLog;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetailLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditHongpayDetailLogMapper {
    Long countByExample(AuditHongpayDetailLogCriteria example) throws DataAccessException;

    int deleteByExample(AuditHongpayDetailLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditHongpayDetailLog record) throws DataAccessException;

    int insertSelective(AuditHongpayDetailLog record) throws DataAccessException;

    List<AuditHongpayDetailLog> selectByExample(AuditHongpayDetailLogCriteria example) throws DataAccessException;

    AuditHongpayDetailLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditHongpayDetailLog record, @Param("example") AuditHongpayDetailLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditHongpayDetailLog record, @Param("example") AuditHongpayDetailLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditHongpayDetailLog record) throws DataAccessException;

    int updateByPrimaryKey(AuditHongpayDetailLog record) throws DataAccessException;
}