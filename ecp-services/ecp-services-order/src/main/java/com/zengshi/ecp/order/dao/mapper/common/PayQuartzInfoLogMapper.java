package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.PayQuartzInfoLog;
import com.zengshi.ecp.order.dao.model.PayQuartzInfoLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayQuartzInfoLogMapper {
    Long countByExample(PayQuartzInfoLogCriteria example) throws DataAccessException;

    int deleteByExample(PayQuartzInfoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayQuartzInfoLog record) throws DataAccessException;

    int insertSelective(PayQuartzInfoLog record) throws DataAccessException;

    List<PayQuartzInfoLog> selectByExample(PayQuartzInfoLogCriteria example) throws DataAccessException;

    PayQuartzInfoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayQuartzInfoLog record, @Param("example") PayQuartzInfoLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayQuartzInfoLog record, @Param("example") PayQuartzInfoLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayQuartzInfoLog record) throws DataAccessException;

    int updateByPrimaryKey(PayQuartzInfoLog record) throws DataAccessException;
}