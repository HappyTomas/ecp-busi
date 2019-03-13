package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPageAttrPub;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPageAttrPubMapper {
    Long countByExample(CmsPageAttrPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsPageAttrPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPageAttrPub record) throws DataAccessException;

    int insertSelective(CmsPageAttrPub record) throws DataAccessException;

    List<CmsPageAttrPub> selectByExample(CmsPageAttrPubCriteria example) throws DataAccessException;

    CmsPageAttrPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPageAttrPub record, @Param("example") CmsPageAttrPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPageAttrPub record, @Param("example") CmsPageAttrPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPageAttrPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsPageAttrPub record) throws DataAccessException;
}
