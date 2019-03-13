package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dao.model.OrdEntityImportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdEntityImportMapper {
    Long countByExample(OrdEntityImportCriteria example) throws DataAccessException;

    int deleteByExample(OrdEntityImportCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdEntityImport record) throws DataAccessException;

    int insertSelective(OrdEntityImport record) throws DataAccessException;

    List<OrdEntityImport> selectByExample(OrdEntityImportCriteria example) throws DataAccessException;

    OrdEntityImport selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdEntityImport record, @Param("example") OrdEntityImportCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdEntityImport record, @Param("example") OrdEntityImportCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdEntityImport record) throws DataAccessException;

    int updateByPrimaryKey(OrdEntityImport record) throws DataAccessException;
}