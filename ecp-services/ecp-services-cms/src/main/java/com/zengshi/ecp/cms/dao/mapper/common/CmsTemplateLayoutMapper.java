package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsTemplateLayout;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsTemplateLayoutMapper {
    Long countByExample(CmsTemplateLayoutCriteria example) throws DataAccessException;

    int deleteByExample(CmsTemplateLayoutCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsTemplateLayout record) throws DataAccessException;

    int insertSelective(CmsTemplateLayout record) throws DataAccessException;

    List<CmsTemplateLayout> selectByExample(CmsTemplateLayoutCriteria example) throws DataAccessException;

    CmsTemplateLayout selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsTemplateLayout record, @Param("example") CmsTemplateLayoutCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsTemplateLayout record, @Param("example") CmsTemplateLayoutCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsTemplateLayout record) throws DataAccessException;

    int updateByPrimaryKey(CmsTemplateLayout record) throws DataAccessException;
}
