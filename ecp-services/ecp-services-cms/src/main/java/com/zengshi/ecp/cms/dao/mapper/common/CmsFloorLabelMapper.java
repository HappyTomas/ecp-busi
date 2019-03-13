package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorLabel;
import com.zengshi.ecp.cms.dao.model.CmsFloorLabelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorLabelMapper {
    Long countByExample(CmsFloorLabelCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorLabelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorLabel record) throws DataAccessException;

    int insertSelective(CmsFloorLabel record) throws DataAccessException;

    List<CmsFloorLabel> selectByExample(CmsFloorLabelCriteria example) throws DataAccessException;

    CmsFloorLabel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorLabel record, @Param("example") CmsFloorLabelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorLabel record, @Param("example") CmsFloorLabelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorLabel record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorLabel record) throws DataAccessException;
}
