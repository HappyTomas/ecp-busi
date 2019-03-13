package com.zengshi.ecp.sys.dao.mapper.busi;

import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dao.model.MsgSendDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface MsgSendDetailMapper {
    Long countByExample(MsgSendDetailCriteria example) throws DataAccessException;

    int deleteByExample(MsgSendDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long msgDetailId) throws DataAccessException;

    int insert(MsgSendDetail record) throws DataAccessException;

    int insertSelective(MsgSendDetail record) throws DataAccessException;

    List<MsgSendDetail> selectByExample(MsgSendDetailCriteria example) throws DataAccessException;

    MsgSendDetail selectByPrimaryKey(Long msgDetailId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgSendDetail record, @Param("example") MsgSendDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgSendDetail record, @Param("example") MsgSendDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgSendDetail record) throws DataAccessException;

    int updateByPrimaryKey(MsgSendDetail record) throws DataAccessException;
}
