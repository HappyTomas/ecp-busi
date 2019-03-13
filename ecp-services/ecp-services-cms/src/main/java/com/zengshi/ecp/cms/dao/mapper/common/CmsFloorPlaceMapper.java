package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorPlace;
import com.zengshi.ecp.cms.dao.model.CmsFloorPlaceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorPlaceMapper {
    Long countByExample(CmsFloorPlaceCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorPlaceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorPlace record) throws DataAccessException;

    int insertSelective(CmsFloorPlace record) throws DataAccessException;

    List<CmsFloorPlace> selectByExample(CmsFloorPlaceCriteria example) throws DataAccessException;

    CmsFloorPlace selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorPlace record, @Param("example") CmsFloorPlaceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorPlace record, @Param("example") CmsFloorPlaceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorPlace record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorPlace record) throws DataAccessException;
}
