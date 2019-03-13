package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgMail;
import com.zengshi.ecp.sys.dao.model.MsgMailCriteria;

public interface MsgMailMapper {
    Long countByExample(MsgMailCriteria example) throws DataAccessException;

    int deleteByExample(MsgMailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String msgCode) throws DataAccessException;

    int insert(MsgMail record) throws DataAccessException;

    int insertSelective(MsgMail record) throws DataAccessException;

    List<MsgMail> selectByExample(MsgMailCriteria example) throws DataAccessException;

    MsgMail selectByPrimaryKey(String msgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgMail record, @Param("example") MsgMailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgMail record, @Param("example") MsgMailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgMail record) throws DataAccessException;

    int updateByPrimaryKey(MsgMail record) throws DataAccessException;
}
