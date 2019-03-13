package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsArticle;
import com.zengshi.ecp.cms.dao.model.CmsArticleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsArticleMapper {
    Long countByExample(CmsArticleCriteria example) throws DataAccessException;

    int deleteByExample(CmsArticleCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsArticle record) throws DataAccessException;

    int insertSelective(CmsArticle record) throws DataAccessException;

    List<CmsArticle> selectByExample(CmsArticleCriteria example) throws DataAccessException;

    CmsArticle selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsArticle record, @Param("example") CmsArticleCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsArticle record, @Param("example") CmsArticleCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsArticle record) throws DataAccessException;

    int updateByPrimaryKey(CmsArticle record) throws DataAccessException;
}
