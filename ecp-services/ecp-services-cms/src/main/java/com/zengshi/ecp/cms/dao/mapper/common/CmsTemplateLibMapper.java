package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsTemplateLib;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLibCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsTemplateLibMapper {
    Long countByExample(CmsTemplateLibCriteria example) throws DataAccessException;

    int deleteByExample(CmsTemplateLibCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsTemplateLib record) throws DataAccessException;

    int insertSelective(CmsTemplateLib record) throws DataAccessException;

    List<CmsTemplateLib> selectByExample(CmsTemplateLibCriteria example) throws DataAccessException;

    CmsTemplateLib selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsTemplateLib record, @Param("example") CmsTemplateLibCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsTemplateLib record, @Param("example") CmsTemplateLibCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsTemplateLib record) throws DataAccessException;

    int updateByPrimaryKey(CmsTemplateLib record) throws DataAccessException;
}
