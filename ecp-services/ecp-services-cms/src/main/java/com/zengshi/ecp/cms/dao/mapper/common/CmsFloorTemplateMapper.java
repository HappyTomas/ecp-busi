package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsFloorTemplate;
import com.zengshi.ecp.cms.dao.model.CmsFloorTemplateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsFloorTemplateMapper {
    Long countByExample(CmsFloorTemplateCriteria example) throws DataAccessException;

    int deleteByExample(CmsFloorTemplateCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsFloorTemplate record) throws DataAccessException;

    int insertSelective(CmsFloorTemplate record) throws DataAccessException;

    List<CmsFloorTemplate> selectByExample(CmsFloorTemplateCriteria example) throws DataAccessException;

    CmsFloorTemplate selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsFloorTemplate record, @Param("example") CmsFloorTemplateCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsFloorTemplate record, @Param("example") CmsFloorTemplateCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsFloorTemplate record) throws DataAccessException;

    int updateByPrimaryKey(CmsFloorTemplate record) throws DataAccessException;
}
