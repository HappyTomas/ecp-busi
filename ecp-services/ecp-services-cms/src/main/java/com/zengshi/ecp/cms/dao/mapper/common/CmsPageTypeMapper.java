package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPageType;
import com.zengshi.ecp.cms.dao.model.CmsPageTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPageTypeMapper {
    Long countByExample(CmsPageTypeCriteria example) throws DataAccessException;

    int deleteByExample(CmsPageTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPageType record) throws DataAccessException;

    int insertSelective(CmsPageType record) throws DataAccessException;

    List<CmsPageType> selectByExample(CmsPageTypeCriteria example) throws DataAccessException;

    CmsPageType selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPageType record, @Param("example") CmsPageTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPageType record, @Param("example") CmsPageTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPageType record) throws DataAccessException;

    int updateByPrimaryKey(CmsPageType record) throws DataAccessException;
}
