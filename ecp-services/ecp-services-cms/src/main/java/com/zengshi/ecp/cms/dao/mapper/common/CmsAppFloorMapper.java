package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsAppFloor;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsAppFloorMapper {
    Long countByExample(CmsAppFloorCriteria example) throws DataAccessException;

    int deleteByExample(CmsAppFloorCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsAppFloor record) throws DataAccessException;

    int insertSelective(CmsAppFloor record) throws DataAccessException;

    List<CmsAppFloor> selectByExample(CmsAppFloorCriteria example) throws DataAccessException;

    CmsAppFloor selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsAppFloor record, @Param("example") CmsAppFloorCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsAppFloor record, @Param("example") CmsAppFloorCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsAppFloor record) throws DataAccessException;

    int updateByPrimaryKey(CmsAppFloor record) throws DataAccessException;
}
