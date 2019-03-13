package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorAttrCount;
import com.zengshi.ecp.cms.dao.model.CmsFloorAttrCountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorAttrCountMapper {
    Long countByExample(CmsFloorAttrCountCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorAttrCountCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorAttrCount record) throws DataAccessException;

    int insertSelective(CmsFloorAttrCount record) throws DataAccessException;

    List<CmsFloorAttrCount> selectByExample(CmsFloorAttrCountCriteria example) throws DataAccessException;

    CmsFloorAttrCount selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorAttrCount record, @Param("example") CmsFloorAttrCountCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorAttrCount record, @Param("example") CmsFloorAttrCountCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorAttrCount record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorAttrCount record) throws DataAccessException;
}
