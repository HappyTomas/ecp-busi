package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsModularParaProp;
import com.zengshi.ecp.cms.dao.model.CmsModularParaPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsModularParaPropMapper {
    Long countByExample(CmsModularParaPropCriteria example) throws DataAccessException;

    int deleteByExample(CmsModularParaPropCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsModularParaProp record) throws DataAccessException;

    int insertSelective(CmsModularParaProp record) throws DataAccessException;

    List<CmsModularParaProp> selectByExample(CmsModularParaPropCriteria example) throws DataAccessException;

    CmsModularParaProp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsModularParaProp record, @Param("example") CmsModularParaPropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsModularParaProp record, @Param("example") CmsModularParaPropCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsModularParaProp record) throws DataAccessException;

    int updateByPrimaryKey(CmsModularParaProp record) throws DataAccessException;
}
