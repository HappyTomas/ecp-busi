package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayBindTransfer;
import com.zengshi.ecp.order.dao.model.PayBindTransferCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayBindTransferMapper {
    Long countByExample(PayBindTransferCriteria example) throws DataAccessException;

    int deleteByExample(PayBindTransferCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayBindTransfer record) throws DataAccessException;

    int insertSelective(PayBindTransfer record) throws DataAccessException;

    List<PayBindTransfer> selectByExample(PayBindTransferCriteria example) throws DataAccessException;

    PayBindTransfer selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayBindTransfer record, @Param("example") PayBindTransferCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayBindTransfer record, @Param("example") PayBindTransferCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayBindTransfer record) throws DataAccessException;

    int updateByPrimaryKey(PayBindTransfer record) throws DataAccessException;
}