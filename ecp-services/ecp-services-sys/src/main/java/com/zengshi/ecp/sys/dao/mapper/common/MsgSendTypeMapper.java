package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgSendType;
import com.zengshi.ecp.sys.dao.model.MsgSendTypeCriteria;

public interface MsgSendTypeMapper {
    Long countByExample(MsgSendTypeCriteria example) throws DataAccessException;

    int deleteByExample(MsgSendTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String sendType) throws DataAccessException;

    int insert(MsgSendType record) throws DataAccessException;

    int insertSelective(MsgSendType record) throws DataAccessException;

    List<MsgSendType> selectByExample(MsgSendTypeCriteria example) throws DataAccessException;

    MsgSendType selectByPrimaryKey(String sendType) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgSendType record, @Param("example") MsgSendTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgSendType record, @Param("example") MsgSendTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgSendType record) throws DataAccessException;

    int updateByPrimaryKey(MsgSendType record) throws DataAccessException;
}
