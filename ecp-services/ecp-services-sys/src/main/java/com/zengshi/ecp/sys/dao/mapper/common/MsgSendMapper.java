package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dao.model.MsgSendCriteria;
import com.zengshi.ecp.sys.dao.model.MsgSendKey;

public interface MsgSendMapper {
    Long countByExample(MsgSendCriteria example) throws DataAccessException;

    int deleteByExample(MsgSendCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(MsgSendKey key) throws DataAccessException;

    int insert(MsgSend record) throws DataAccessException;

    int insertSelective(MsgSend record) throws DataAccessException;

    List<MsgSend> selectByExample(MsgSendCriteria example) throws DataAccessException;

    MsgSend selectByPrimaryKey(MsgSendKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgSend record, @Param("example") MsgSendCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgSend record, @Param("example") MsgSendCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgSend record) throws DataAccessException;

    int updateByPrimaryKey(MsgSend record) throws DataAccessException;
}
