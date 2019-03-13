package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromImportLog;
import com.zengshi.ecp.prom.dao.model.PromImportLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromImportLogMapper {
    Long countByExample(PromImportLogCriteria example) throws DataAccessException;

    int deleteByExample(PromImportLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromImportLog record) throws DataAccessException;

    int insertSelective(PromImportLog record) throws DataAccessException;

    List<PromImportLog> selectByExample(PromImportLogCriteria example) throws DataAccessException;

    PromImportLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromImportLog record, @Param("example") PromImportLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromImportLog record, @Param("example") PromImportLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromImportLog record) throws DataAccessException;

    int updateByPrimaryKey(PromImportLog record) throws DataAccessException;
}