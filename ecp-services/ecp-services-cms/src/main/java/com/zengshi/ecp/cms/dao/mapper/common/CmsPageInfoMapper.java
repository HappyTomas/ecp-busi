package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPageInfo;
import com.zengshi.ecp.cms.dao.model.CmsPageInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPageInfoMapper {
    Long countByExample(CmsPageInfoCriteria example) throws DataAccessException;

    int deleteByExample(CmsPageInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPageInfo record) throws DataAccessException;

    int insertSelective(CmsPageInfo record) throws DataAccessException;

    List<CmsPageInfo> selectByExample(CmsPageInfoCriteria example) throws DataAccessException;

    CmsPageInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPageInfo record, @Param("example") CmsPageInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPageInfo record, @Param("example") CmsPageInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPageInfo record) throws DataAccessException;

    int updateByPrimaryKey(CmsPageInfo record) throws DataAccessException;
}
