package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutItemPubMapper {
    Long countByExample(CmsLayoutItemPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutItemPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutItemPub record) throws DataAccessException;

    int insertSelective(CmsLayoutItemPub record) throws DataAccessException;

    List<CmsLayoutItemPub> selectByExample(CmsLayoutItemPubCriteria example) throws DataAccessException;

    CmsLayoutItemPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutItemPub record, @Param("example") CmsLayoutItemPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutItemPub record, @Param("example") CmsLayoutItemPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutItemPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutItemPub record) throws DataAccessException;
}
