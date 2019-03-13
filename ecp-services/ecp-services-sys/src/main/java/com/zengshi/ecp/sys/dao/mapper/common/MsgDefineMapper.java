package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgDefine;
import com.zengshi.ecp.sys.dao.model.MsgDefineCriteria;

public interface MsgDefineMapper {
    Long countByExample(MsgDefineCriteria example) throws DataAccessException;

    int deleteByExample(MsgDefineCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String msgCode) throws DataAccessException;

    int insert(MsgDefine record) throws DataAccessException;

    int insertSelective(MsgDefine record) throws DataAccessException;

    List<MsgDefine> selectByExample(MsgDefineCriteria example) throws DataAccessException;

    MsgDefine selectByPrimaryKey(String msgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgDefine record, @Param("example") MsgDefineCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgDefine record, @Param("example") MsgDefineCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgDefine record) throws DataAccessException;

    int updateByPrimaryKey(MsgDefine record) throws DataAccessException;
}
