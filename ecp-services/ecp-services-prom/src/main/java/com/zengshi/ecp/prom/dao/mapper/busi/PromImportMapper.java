package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromImport;
import com.zengshi.ecp.prom.dao.model.PromImportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromImportMapper {
    Long countByExample(PromImportCriteria example) throws DataAccessException;

    int deleteByExample(PromImportCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromImport record) throws DataAccessException;

    int insertSelective(PromImport record) throws DataAccessException;

    List<PromImport> selectByExample(PromImportCriteria example) throws DataAccessException;

    PromImport selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromImport record, @Param("example") PromImportCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromImport record, @Param("example") PromImportCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromImport record) throws DataAccessException;

    int updateByPrimaryKey(PromImport record) throws DataAccessException;
}