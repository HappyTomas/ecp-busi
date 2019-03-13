package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPlaceCategory;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPlaceCategoryMapper {
    Long countByExample(CmsPlaceCategoryCriteria example) throws DataAccessException;

    int deleteByExample(CmsPlaceCategoryCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPlaceCategory record) throws DataAccessException;

    int insertSelective(CmsPlaceCategory record) throws DataAccessException;

    List<CmsPlaceCategory> selectByExample(CmsPlaceCategoryCriteria example) throws DataAccessException;

    CmsPlaceCategory selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPlaceCategory record, @Param("example") CmsPlaceCategoryCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPlaceCategory record, @Param("example") CmsPlaceCategoryCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPlaceCategory record) throws DataAccessException;

    int updateByPrimaryKey(CmsPlaceCategory record) throws DataAccessException;
}
