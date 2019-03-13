package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsItemPropPre;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsItemPropPreMapper {
    Long countByExample(CmsItemPropPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsItemPropPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsItemPropPre record) throws DataAccessException;

    int insertSelective(CmsItemPropPre record) throws DataAccessException;

    List<CmsItemPropPre> selectByExample(CmsItemPropPreCriteria example) throws DataAccessException;

    CmsItemPropPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsItemPropPre record, @Param("example") CmsItemPropPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsItemPropPre record, @Param("example") CmsItemPropPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsItemPropPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsItemPropPre record) throws DataAccessException;
}
