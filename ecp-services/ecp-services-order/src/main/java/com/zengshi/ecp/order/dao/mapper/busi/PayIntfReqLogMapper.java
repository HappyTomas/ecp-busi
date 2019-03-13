package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.PayIntfReqLog;
import com.zengshi.ecp.order.dao.model.PayIntfReqLogCriteria;
import com.zengshi.ecp.order.dao.model.PayIntfReqLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PayIntfReqLogMapper {
    Long countByExample(PayIntfReqLogCriteria example) throws DataAccessException;

    int deleteByExample(PayIntfReqLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PayIntfReqLogWithBLOBs record) throws DataAccessException;

    int insertSelective(PayIntfReqLogWithBLOBs record) throws DataAccessException;

    List<PayIntfReqLogWithBLOBs> selectByExampleWithBLOBs(PayIntfReqLogCriteria example) throws DataAccessException;

    List<PayIntfReqLog> selectByExample(PayIntfReqLogCriteria example) throws DataAccessException;

    PayIntfReqLogWithBLOBs selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PayIntfReqLogWithBLOBs record, @Param("example") PayIntfReqLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") PayIntfReqLogWithBLOBs record, @Param("example") PayIntfReqLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PayIntfReqLog record, @Param("example") PayIntfReqLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PayIntfReqLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(PayIntfReqLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(PayIntfReqLog record) throws DataAccessException;
}