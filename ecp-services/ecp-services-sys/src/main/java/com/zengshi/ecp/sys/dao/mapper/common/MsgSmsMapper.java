package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgSms;
import com.zengshi.ecp.sys.dao.model.MsgSmsCriteria;

public interface MsgSmsMapper {
    Long countByExample(MsgSmsCriteria example) throws DataAccessException;

    int deleteByExample(MsgSmsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String msgCode) throws DataAccessException;

    int insert(MsgSms record) throws DataAccessException;

    int insertSelective(MsgSms record) throws DataAccessException;

    List<MsgSms> selectByExample(MsgSmsCriteria example) throws DataAccessException;

    MsgSms selectByPrimaryKey(String msgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgSms record, @Param("example") MsgSmsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgSms record, @Param("example") MsgSmsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgSms record) throws DataAccessException;

    int updateByPrimaryKey(MsgSms record) throws DataAccessException;
}
