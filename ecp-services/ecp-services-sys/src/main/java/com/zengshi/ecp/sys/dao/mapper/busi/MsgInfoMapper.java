package com.zengshi.ecp.sys.dao.mapper.busi;

import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface MsgInfoMapper {
    Long countByExample(MsgInfoCriteria example) throws DataAccessException;

    int deleteByExample(MsgInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long msgInfoId) throws DataAccessException;

    int insert(MsgInfo record) throws DataAccessException;

    int insertSelective(MsgInfo record) throws DataAccessException;

    List<MsgInfo> selectByExample(MsgInfoCriteria example) throws DataAccessException;

    MsgInfo selectByPrimaryKey(Long msgInfoId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgInfo record, @Param("example") MsgInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgInfo record, @Param("example") MsgInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgInfo record) throws DataAccessException;

    int updateByPrimaryKey(MsgInfo record) throws DataAccessException;
}
