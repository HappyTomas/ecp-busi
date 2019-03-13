package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayIntfNotifyLog;
import com.zengshi.ecp.order.dao.model.PayIntfNotifyLogCriteria;
import com.zengshi.ecp.order.dao.model.PayIntfNotifyLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayIntfNotifyLogMapper {
    Long countByExample(PayIntfNotifyLogCriteria example) throws DataAccessException;

    int deleteByExample(PayIntfNotifyLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayIntfNotifyLogWithBLOBs record) throws DataAccessException;

    int insertSelective(PayIntfNotifyLogWithBLOBs record) throws DataAccessException;

    List<PayIntfNotifyLogWithBLOBs> selectByExampleWithBLOBs(PayIntfNotifyLogCriteria example) throws DataAccessException;

    List<PayIntfNotifyLog> selectByExample(PayIntfNotifyLogCriteria example) throws DataAccessException;

    PayIntfNotifyLogWithBLOBs selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayIntfNotifyLogWithBLOBs record, @Param("example") PayIntfNotifyLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") PayIntfNotifyLogWithBLOBs record, @Param("example") PayIntfNotifyLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayIntfNotifyLog record, @Param("example") PayIntfNotifyLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayIntfNotifyLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(PayIntfNotifyLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(PayIntfNotifyLog record) throws DataAccessException;
}