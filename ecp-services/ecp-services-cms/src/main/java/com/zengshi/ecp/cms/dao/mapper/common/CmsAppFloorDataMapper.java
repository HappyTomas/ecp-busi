package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsAppFloorData;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorDataCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsAppFloorDataMapper {
    Long countByExample(CmsAppFloorDataCriteria example) throws DataAccessException;

    int deleteByExample(CmsAppFloorDataCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsAppFloorData record) throws DataAccessException;

    int insertSelective(CmsAppFloorData record) throws DataAccessException;

    List<CmsAppFloorData> selectByExample(CmsAppFloorDataCriteria example) throws DataAccessException;

    CmsAppFloorData selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsAppFloorData record, @Param("example") CmsAppFloorDataCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsAppFloorData record, @Param("example") CmsAppFloorDataCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsAppFloorData record) throws DataAccessException;

    int updateByPrimaryKey(CmsAppFloorData record) throws DataAccessException;
}
