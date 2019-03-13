package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorGds;
import com.zengshi.ecp.cms.dao.model.CmsFloorGdsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorGdsMapper {
    Long countByExample(CmsFloorGdsCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorGdsCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorGds record) throws DataAccessException;

    int insertSelective(CmsFloorGds record) throws DataAccessException;

    List<CmsFloorGds> selectByExample(CmsFloorGdsCriteria example) throws DataAccessException;

    CmsFloorGds selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorGds record, @Param("example") CmsFloorGdsCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorGds record, @Param("example") CmsFloorGdsCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorGds record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorGds record) throws DataAccessException;
}
