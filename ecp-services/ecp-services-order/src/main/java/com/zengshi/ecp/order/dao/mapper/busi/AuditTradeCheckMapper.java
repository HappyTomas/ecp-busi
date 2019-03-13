package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.AuditTradeCheck;
import com.zengshi.ecp.order.dao.model.AuditTradeCheckCriteria;
import com.zengshi.ecp.order.dao.model.AuditTradeCheckKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditTradeCheckMapper {
    Long countByExample(AuditTradeCheckCriteria example) throws DataAccessException;

    int deleteByExample(AuditTradeCheckCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuditTradeCheckKey key) throws DataAccessException;

    int insert(AuditTradeCheck record) throws DataAccessException;

    int insertSelective(AuditTradeCheck record) throws DataAccessException;

    List<AuditTradeCheck> selectByExample(AuditTradeCheckCriteria example) throws DataAccessException;

    AuditTradeCheck selectByPrimaryKey(AuditTradeCheckKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditTradeCheck record, @Param("example") AuditTradeCheckCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditTradeCheck record, @Param("example") AuditTradeCheckCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditTradeCheck record) throws DataAccessException;

    int updateByPrimaryKey(AuditTradeCheck record) throws DataAccessException;
}