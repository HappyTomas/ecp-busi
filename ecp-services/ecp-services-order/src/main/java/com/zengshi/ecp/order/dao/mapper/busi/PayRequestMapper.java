package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayRequest;
import com.zengshi.ecp.order.dao.model.PayRequestCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayRequestMapper {
    Long countByExample(PayRequestCriteria example) throws DataAccessException;

    int deleteByExample(PayRequestCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayRequest record) throws DataAccessException;

    int insertSelective(PayRequest record) throws DataAccessException;

    List<PayRequest> selectByExample(PayRequestCriteria example) throws DataAccessException;

    PayRequest selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayRequest record, @Param("example") PayRequestCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayRequest record, @Param("example") PayRequestCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayRequest record) throws DataAccessException;

    int updateByPrimaryKey(PayRequest record) throws DataAccessException;
}