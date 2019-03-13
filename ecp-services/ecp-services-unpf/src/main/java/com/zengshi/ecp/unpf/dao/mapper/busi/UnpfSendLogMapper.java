package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfSendLog;
import com.zengshi.ecp.unpf.dao.model.UnpfSendLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfSendLogMapper {
    Long countByExample(UnpfSendLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfSendLog record) throws DataAccessException;

    int insertSelective(UnpfSendLog record) throws DataAccessException;

    List<UnpfSendLog> selectByExampleWithBLOBs(UnpfSendLogCriteria example) throws DataAccessException;

    List<UnpfSendLog> selectByExample(UnpfSendLogCriteria example) throws DataAccessException;

    UnpfSendLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfSendLog record, @Param("example") UnpfSendLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") UnpfSendLog record, @Param("example") UnpfSendLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfSendLog record, @Param("example") UnpfSendLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfSendLog record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(UnpfSendLog record) throws DataAccessException;

    int updateByPrimaryKey(UnpfSendLog record) throws DataAccessException;
}