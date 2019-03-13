package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItem;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsTemplateLayoutItemMapper {
    Long countByExample(CmsTemplateLayoutItemCriteria example) throws DataAccessException;

    int deleteByExample(CmsTemplateLayoutItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsTemplateLayoutItem record) throws DataAccessException;

    int insertSelective(CmsTemplateLayoutItem record) throws DataAccessException;

    List<CmsTemplateLayoutItem> selectByExample(CmsTemplateLayoutItemCriteria example) throws DataAccessException;

    CmsTemplateLayoutItem selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsTemplateLayoutItem record, @Param("example") CmsTemplateLayoutItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsTemplateLayoutItem record, @Param("example") CmsTemplateLayoutItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsTemplateLayoutItem record) throws DataAccessException;

    int updateByPrimaryKey(CmsTemplateLayoutItem record) throws DataAccessException;
}
