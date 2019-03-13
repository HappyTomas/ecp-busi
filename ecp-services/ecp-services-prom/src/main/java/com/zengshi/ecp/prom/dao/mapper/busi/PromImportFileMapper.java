package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromImportFile;
import com.zengshi.ecp.prom.dao.model.PromImportFileCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromImportFileMapper {
    Long countByExample(PromImportFileCriteria example) throws DataAccessException;

    int deleteByExample(PromImportFileCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String fileId) throws DataAccessException;

    int insert(PromImportFile record) throws DataAccessException;

    int insertSelective(PromImportFile record) throws DataAccessException;

    List<PromImportFile> selectByExample(PromImportFileCriteria example) throws DataAccessException;

    PromImportFile selectByPrimaryKey(String fileId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromImportFile record, @Param("example") PromImportFileCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromImportFile record, @Param("example") PromImportFileCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromImportFile record) throws DataAccessException;

    int updateByPrimaryKey(PromImportFile record) throws DataAccessException;
}