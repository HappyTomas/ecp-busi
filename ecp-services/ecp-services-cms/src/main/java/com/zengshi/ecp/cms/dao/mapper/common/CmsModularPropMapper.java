package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsModularProp;
import com.zengshi.ecp.cms.dao.model.CmsModularPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsModularPropMapper {
    Long countByExample(CmsModularPropCriteria example) throws DataAccessException;

    int deleteByExample(CmsModularPropCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsModularProp record) throws DataAccessException;

    int insertSelective(CmsModularProp record) throws DataAccessException;

    List<CmsModularProp> selectByExample(CmsModularPropCriteria example) throws DataAccessException;

    CmsModularProp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsModularProp record, @Param("example") CmsModularPropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsModularProp record, @Param("example") CmsModularPropCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsModularProp record) throws DataAccessException;

    int updateByPrimaryKey(CmsModularProp record) throws DataAccessException;
}
