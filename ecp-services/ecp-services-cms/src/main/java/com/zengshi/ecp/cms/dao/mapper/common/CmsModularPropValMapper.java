package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsModularPropVal;
import com.zengshi.ecp.cms.dao.model.CmsModularPropValCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsModularPropValMapper {
    Long countByExample(CmsModularPropValCriteria example) throws DataAccessException;

    int deleteByExample(CmsModularPropValCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsModularPropVal record) throws DataAccessException;

    int insertSelective(CmsModularPropVal record) throws DataAccessException;

    List<CmsModularPropVal> selectByExample(CmsModularPropValCriteria example) throws DataAccessException;

    CmsModularPropVal selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsModularPropVal record, @Param("example") CmsModularPropValCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsModularPropVal record, @Param("example") CmsModularPropValCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsModularPropVal record) throws DataAccessException;

    int updateByPrimaryKey(CmsModularPropVal record) throws DataAccessException;
}
