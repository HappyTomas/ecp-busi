package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dao.model.OrdFileImportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdFileImportMapper {
    Long countByExample(OrdFileImportCriteria example) throws DataAccessException;

    int deleteByExample(OrdFileImportCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdFileImport record) throws DataAccessException;

    int insertSelective(OrdFileImport record) throws DataAccessException;

    List<OrdFileImport> selectByExample(OrdFileImportCriteria example) throws DataAccessException;

    OrdFileImport selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdFileImport record, @Param("example") OrdFileImportCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdFileImport record, @Param("example") OrdFileImportCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdFileImport record) throws DataAccessException;

    int updateByPrimaryKey(OrdFileImport record) throws DataAccessException;
}