package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfOrdLog;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfOrdLogMapper {
    Long countByExample(UnpfOrdLogCriteria example) throws DataAccessException;

    int deleteByExample(UnpfOrdLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfOrdLog record) throws DataAccessException;

    int insertSelective(UnpfOrdLog record) throws DataAccessException;

    List<UnpfOrdLog> selectByExampleWithBLOBs(UnpfOrdLogCriteria example) throws DataAccessException;

    List<UnpfOrdLog> selectByExample(UnpfOrdLogCriteria example) throws DataAccessException;

    UnpfOrdLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfOrdLog record, @Param("example") UnpfOrdLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") UnpfOrdLog record, @Param("example") UnpfOrdLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfOrdLog record, @Param("example") UnpfOrdLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfOrdLog record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(UnpfOrdLog record) throws DataAccessException;

    int updateByPrimaryKey(UnpfOrdLog record) throws DataAccessException;
}