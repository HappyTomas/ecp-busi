package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutPubMapper {
    Long countByExample(CmsLayoutPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutPub record) throws DataAccessException;

    int insertSelective(CmsLayoutPub record) throws DataAccessException;

    List<CmsLayoutPub> selectByExample(CmsLayoutPubCriteria example) throws DataAccessException;

    CmsLayoutPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutPub record, @Param("example") CmsLayoutPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutPub record, @Param("example") CmsLayoutPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutPub record) throws DataAccessException;
}
