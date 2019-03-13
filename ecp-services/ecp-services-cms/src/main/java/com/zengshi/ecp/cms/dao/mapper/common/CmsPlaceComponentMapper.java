package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPlaceComponent;
import com.zengshi.ecp.cms.dao.model.CmsPlaceComponentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPlaceComponentMapper {
    Long countByExample(CmsPlaceComponentCriteria example) throws DataAccessException;

    int deleteByExample(CmsPlaceComponentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPlaceComponent record) throws DataAccessException;

    int insertSelective(CmsPlaceComponent record) throws DataAccessException;

    List<CmsPlaceComponent> selectByExample(CmsPlaceComponentCriteria example) throws DataAccessException;

    CmsPlaceComponent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPlaceComponent record, @Param("example") CmsPlaceComponentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPlaceComponent record, @Param("example") CmsPlaceComponentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPlaceComponent record) throws DataAccessException;

    int updateByPrimaryKey(CmsPlaceComponent record) throws DataAccessException;
}
