package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorAdvertise;
import com.zengshi.ecp.cms.dao.model.CmsFloorAdvertiseCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorAdvertiseMapper {
    Long countByExample(CmsFloorAdvertiseCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorAdvertiseCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorAdvertise record) throws DataAccessException;

    int insertSelective(CmsFloorAdvertise record) throws DataAccessException;

    List<CmsFloorAdvertise> selectByExample(CmsFloorAdvertiseCriteria example) throws DataAccessException;

    CmsFloorAdvertise selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorAdvertise record, @Param("example") CmsFloorAdvertiseCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorAdvertise record, @Param("example") CmsFloorAdvertiseCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorAdvertise record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorAdvertise record) throws DataAccessException;
}
