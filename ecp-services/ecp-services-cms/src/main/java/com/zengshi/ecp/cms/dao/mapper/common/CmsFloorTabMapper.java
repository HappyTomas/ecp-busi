package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorTab;
import com.zengshi.ecp.cms.dao.model.CmsFloorTabCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorTabMapper {
    Long countByExample(CmsFloorTabCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorTabCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorTab record) throws DataAccessException;

    int insertSelective(CmsFloorTab record) throws DataAccessException;

    List<CmsFloorTab> selectByExample(CmsFloorTabCriteria example) throws DataAccessException;

    CmsFloorTab selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorTab record, @Param("example") CmsFloorTabCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorTab record, @Param("example") CmsFloorTabCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorTab record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorTab record) throws DataAccessException;
}
