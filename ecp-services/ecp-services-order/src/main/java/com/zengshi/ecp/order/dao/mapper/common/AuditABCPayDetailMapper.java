package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditABCPayDetail;
import com.zengshi.ecp.order.dao.model.AuditABCPayDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditABCPayDetailMapper {
    Long countByExample(AuditABCPayDetailCriteria example) throws DataAccessException;

    int deleteByExample(AuditABCPayDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditABCPayDetail record) throws DataAccessException;

    int insertSelective(AuditABCPayDetail record) throws DataAccessException;

    List<AuditABCPayDetail> selectByExample(AuditABCPayDetailCriteria example) throws DataAccessException;

    AuditABCPayDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditABCPayDetail record, @Param("example") AuditABCPayDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditABCPayDetail record, @Param("example") AuditABCPayDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditABCPayDetail record) throws DataAccessException;

    int updateByPrimaryKey(AuditABCPayDetail record) throws DataAccessException;
}