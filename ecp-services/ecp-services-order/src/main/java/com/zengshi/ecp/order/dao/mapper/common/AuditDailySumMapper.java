package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditDailySum;
import com.zengshi.ecp.order.dao.model.AuditDailySumCriteria;
import com.zengshi.ecp.order.dao.model.AuditDailySumKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditDailySumMapper {
    Long countByExample(AuditDailySumCriteria example) throws DataAccessException;

    int deleteByExample(AuditDailySumCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuditDailySumKey key) throws DataAccessException;

    int insert(AuditDailySum record) throws DataAccessException;

    int insertSelective(AuditDailySum record) throws DataAccessException;

    List<AuditDailySum> selectByExample(AuditDailySumCriteria example) throws DataAccessException;

    AuditDailySum selectByPrimaryKey(AuditDailySumKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditDailySum record, @Param("example") AuditDailySumCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditDailySum record, @Param("example") AuditDailySumCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditDailySum record) throws DataAccessException;

    int updateByPrimaryKey(AuditDailySum record) throws DataAccessException;
}