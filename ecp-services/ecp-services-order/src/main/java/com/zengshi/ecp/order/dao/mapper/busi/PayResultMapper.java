package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayResult;
import com.zengshi.ecp.order.dao.model.PayResultCriteria;
import com.zengshi.ecp.order.dao.model.PayResultKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayResultMapper {
    Long countByExample(PayResultCriteria example) throws DataAccessException;

    int deleteByExample(PayResultCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PayResultKey key) throws DataAccessException;

    int insert(PayResult record) throws DataAccessException;

    int insertSelective(PayResult record) throws DataAccessException;

    List<PayResult> selectByExample(PayResultCriteria example) throws DataAccessException;

    PayResult selectByPrimaryKey(PayResultKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayResult record, @Param("example") PayResultCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayResult record, @Param("example") PayResultCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayResult record) throws DataAccessException;

    int updateByPrimaryKey(PayResult record) throws DataAccessException;
}