package com.zengshi.ecp.sys.dao.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.sys.dao.model.MsgSite;
import com.zengshi.ecp.sys.dao.model.MsgSiteCriteria;

public interface MsgSiteMapper {
    Long countByExample(MsgSiteCriteria example) throws DataAccessException;

    int deleteByExample(MsgSiteCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String msgCode) throws DataAccessException;

    int insert(MsgSite record) throws DataAccessException;

    int insertSelective(MsgSite record) throws DataAccessException;

    List<MsgSite> selectByExample(MsgSiteCriteria example) throws DataAccessException;

    MsgSite selectByPrimaryKey(String msgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgSite record, @Param("example") MsgSiteCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgSite record, @Param("example") MsgSiteCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgSite record) throws DataAccessException;

    int updateByPrimaryKey(MsgSite record) throws DataAccessException;
}
