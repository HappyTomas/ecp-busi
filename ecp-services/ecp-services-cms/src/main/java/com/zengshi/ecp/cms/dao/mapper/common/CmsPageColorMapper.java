package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPageColor;
import com.zengshi.ecp.cms.dao.model.CmsPageColorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPageColorMapper {
    Long countByExample(CmsPageColorCriteria example) throws DataAccessException;

    int deleteByExample(CmsPageColorCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPageColor record) throws DataAccessException;

    int insertSelective(CmsPageColor record) throws DataAccessException;

    List<CmsPageColor> selectByExample(CmsPageColorCriteria example) throws DataAccessException;

    CmsPageColor selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPageColor record, @Param("example") CmsPageColorCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPageColor record, @Param("example") CmsPageColorCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPageColor record) throws DataAccessException;

    int updateByPrimaryKey(CmsPageColor record) throws DataAccessException;
}
