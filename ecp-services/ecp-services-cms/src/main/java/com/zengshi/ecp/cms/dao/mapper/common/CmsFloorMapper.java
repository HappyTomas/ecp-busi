package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloor;
import com.zengshi.ecp.cms.dao.model.CmsFloorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorMapper {
    Long countByExample(CmsFloorCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloor record) throws DataAccessException;

    int insertSelective(CmsFloor record) throws DataAccessException;

    List<CmsFloor> selectByExample(CmsFloorCriteria example) throws DataAccessException;

    CmsFloor selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloor record, @Param("example") CmsFloorCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloor record, @Param("example") CmsFloorCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloor record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloor record) throws DataAccessException;
}
