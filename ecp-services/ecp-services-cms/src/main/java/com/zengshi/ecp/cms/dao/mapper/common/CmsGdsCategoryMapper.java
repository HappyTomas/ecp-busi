package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsGdsCategory;
import com.zengshi.ecp.cms.dao.model.CmsGdsCategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsGdsCategoryMapper {
    Long countByExample(CmsGdsCategoryCriteria example) throws DataAccessException;

    int deleteByExample(CmsGdsCategoryCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(CmsGdsCategory record) throws DataAccessException;

    int insertSelective(CmsGdsCategory record) throws DataAccessException;

    List<CmsGdsCategory> selectByExample(CmsGdsCategoryCriteria example) throws DataAccessException;

    CmsGdsCategory selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsGdsCategory record, @Param("example") CmsGdsCategoryCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsGdsCategory record, @Param("example") CmsGdsCategoryCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsGdsCategory record) throws DataAccessException;

    int updateByPrimaryKey(CmsGdsCategory record) throws DataAccessException;
}
