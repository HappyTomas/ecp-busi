package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsTemplate;
import com.zengshi.ecp.cms.dao.model.CmsTemplateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsTemplateMapper {
    Long countByExample(CmsTemplateCriteria example) throws DataAccessException;

    int deleteByExample(CmsTemplateCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsTemplate record) throws DataAccessException;

    int insertSelective(CmsTemplate record) throws DataAccessException;

    List<CmsTemplate> selectByExample(CmsTemplateCriteria example) throws DataAccessException;

    CmsTemplate selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsTemplate record, @Param("example") CmsTemplateCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsTemplate record, @Param("example") CmsTemplateCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsTemplate record) throws DataAccessException;

    int updateByPrimaryKey(CmsTemplate record) throws DataAccessException;
}
