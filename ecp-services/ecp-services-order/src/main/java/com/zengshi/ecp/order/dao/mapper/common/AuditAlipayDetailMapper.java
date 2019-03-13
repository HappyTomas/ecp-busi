package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditAlipayDetail;
import com.zengshi.ecp.order.dao.model.AuditAlipayDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditAlipayDetailMapper {
    Long countByExample(AuditAlipayDetailCriteria example) throws DataAccessException;

    int deleteByExample(AuditAlipayDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditAlipayDetail record) throws DataAccessException;

    int insertSelective(AuditAlipayDetail record) throws DataAccessException;

    List<AuditAlipayDetail> selectByExample(AuditAlipayDetailCriteria example) throws DataAccessException;

    AuditAlipayDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditAlipayDetail record, @Param("example") AuditAlipayDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditAlipayDetail record, @Param("example") AuditAlipayDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditAlipayDetail record) throws DataAccessException;

    int updateByPrimaryKey(AuditAlipayDetail record) throws DataAccessException;
}