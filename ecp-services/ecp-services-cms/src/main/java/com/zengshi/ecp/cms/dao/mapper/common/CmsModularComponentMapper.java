package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsModularComponent;
import com.zengshi.ecp.cms.dao.model.CmsModularComponentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsModularComponentMapper {
    Long countByExample(CmsModularComponentCriteria example) throws DataAccessException;

    int deleteByExample(CmsModularComponentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsModularComponent record) throws DataAccessException;

    int insertSelective(CmsModularComponent record) throws DataAccessException;

    List<CmsModularComponent> selectByExample(CmsModularComponentCriteria example) throws DataAccessException;

    CmsModularComponent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsModularComponent record, @Param("example") CmsModularComponentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsModularComponent record, @Param("example") CmsModularComponentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsModularComponent record) throws DataAccessException;

    int updateByPrimaryKey(CmsModularComponent record) throws DataAccessException;
}
