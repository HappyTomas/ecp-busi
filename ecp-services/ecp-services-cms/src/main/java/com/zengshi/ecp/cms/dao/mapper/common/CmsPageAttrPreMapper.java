package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPageAttrPre;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPageAttrPreMapper {
    Long countByExample(CmsPageAttrPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsPageAttrPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPageAttrPre record) throws DataAccessException;

    int insertSelective(CmsPageAttrPre record) throws DataAccessException;

    List<CmsPageAttrPre> selectByExample(CmsPageAttrPreCriteria example) throws DataAccessException;

    CmsPageAttrPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPageAttrPre record, @Param("example") CmsPageAttrPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPageAttrPre record, @Param("example") CmsPageAttrPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPageAttrPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsPageAttrPre record) throws DataAccessException;
}
