package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsModular;
import com.zengshi.ecp.cms.dao.model.CmsModularCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsModularMapper {
    Long countByExample(CmsModularCriteria example) throws DataAccessException;

    int deleteByExample(CmsModularCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsModular record) throws DataAccessException;

    int insertSelective(CmsModular record) throws DataAccessException;

    List<CmsModular> selectByExample(CmsModularCriteria example) throws DataAccessException;

    CmsModular selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsModular record, @Param("example") CmsModularCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsModular record, @Param("example") CmsModularCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsModular record) throws DataAccessException;

    int updateByPrimaryKey(CmsModular record) throws DataAccessException;
}
