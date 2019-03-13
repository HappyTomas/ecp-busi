package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayBindLog;
import com.zengshi.ecp.order.dao.model.PayBindLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayBindLogMapper {
    Long countByExample(PayBindLogCriteria example) throws DataAccessException;

    int deleteByExample(PayBindLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayBindLog record) throws DataAccessException;

    int insertSelective(PayBindLog record) throws DataAccessException;

    List<PayBindLog> selectByExample(PayBindLogCriteria example) throws DataAccessException;

    PayBindLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayBindLog record, @Param("example") PayBindLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayBindLog record, @Param("example") PayBindLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayBindLog record) throws DataAccessException;

    int updateByPrimaryKey(PayBindLog record) throws DataAccessException;
}