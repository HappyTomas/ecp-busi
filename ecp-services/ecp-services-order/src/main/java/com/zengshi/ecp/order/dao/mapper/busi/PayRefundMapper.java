package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayRefund;
import com.zengshi.ecp.order.dao.model.PayRefundCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayRefundMapper {
    Long countByExample(PayRefundCriteria example) throws DataAccessException;

    int deleteByExample(PayRefundCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayRefund record) throws DataAccessException;

    int insertSelective(PayRefund record) throws DataAccessException;

    List<PayRefund> selectByExample(PayRefundCriteria example) throws DataAccessException;

    PayRefund selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayRefund record, @Param("example") PayRefundCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayRefund record, @Param("example") PayRefundCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayRefund record) throws DataAccessException;

    int updateByPrimaryKey(PayRefund record) throws DataAccessException;
}