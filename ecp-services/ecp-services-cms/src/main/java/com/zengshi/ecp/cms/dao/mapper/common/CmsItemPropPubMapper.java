package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsItemPropPub;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsItemPropPubMapper {
    Long countByExample(CmsItemPropPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsItemPropPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsItemPropPub record) throws DataAccessException;

    int insertSelective(CmsItemPropPub record) throws DataAccessException;

    List<CmsItemPropPub> selectByExample(CmsItemPropPubCriteria example) throws DataAccessException;

    CmsItemPropPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsItemPropPub record, @Param("example") CmsItemPropPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsItemPropPub record, @Param("example") CmsItemPropPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsItemPropPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsItemPropPub record) throws DataAccessException;
}
