package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dao.model.PayRefundResultCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayRefundResultMapper {
    Long countByExample(PayRefundResultCriteria example) throws DataAccessException;

    int deleteByExample(PayRefundResultCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayRefundResult record) throws DataAccessException;

    int insertSelective(PayRefundResult record) throws DataAccessException;

    List<PayRefundResult> selectByExample(PayRefundResultCriteria example) throws DataAccessException;

    PayRefundResult selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayRefundResult record, @Param("example") PayRefundResultCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayRefundResult record, @Param("example") PayRefundResultCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayRefundResult record) throws DataAccessException;

    int updateByPrimaryKey(PayRefundResult record) throws DataAccessException;
}