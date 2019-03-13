package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutAttrPubMapper {
    Long countByExample(CmsLayoutAttrPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutAttrPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutAttrPub record) throws DataAccessException;

    int insertSelective(CmsLayoutAttrPub record) throws DataAccessException;

    List<CmsLayoutAttrPub> selectByExample(CmsLayoutAttrPubCriteria example) throws DataAccessException;

    CmsLayoutAttrPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutAttrPub record, @Param("example") CmsLayoutAttrPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutAttrPub record, @Param("example") CmsLayoutAttrPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutAttrPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutAttrPub record) throws DataAccessException;
}
