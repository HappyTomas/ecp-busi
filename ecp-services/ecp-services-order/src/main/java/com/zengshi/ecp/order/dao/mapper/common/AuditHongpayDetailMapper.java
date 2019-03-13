package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.AuditHongpayDetail;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuditHongpayDetailMapper {
    Long countByExample(AuditHongpayDetailCriteria example) throws DataAccessException;

    int deleteByExample(AuditHongpayDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuditHongpayDetail record) throws DataAccessException;

    int insertSelective(AuditHongpayDetail record) throws DataAccessException;

    List<AuditHongpayDetail> selectByExample(AuditHongpayDetailCriteria example) throws DataAccessException;

    AuditHongpayDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuditHongpayDetail record, @Param("example") AuditHongpayDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuditHongpayDetail record, @Param("example") AuditHongpayDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuditHongpayDetail record) throws DataAccessException;

    int updateByPrimaryKey(AuditHongpayDetail record) throws DataAccessException;
}