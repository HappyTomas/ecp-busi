package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutAttrPreMapper {
    Long countByExample(CmsLayoutAttrPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutAttrPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutAttrPre record) throws DataAccessException;

    int insertSelective(CmsLayoutAttrPre record) throws DataAccessException;

    List<CmsLayoutAttrPre> selectByExample(CmsLayoutAttrPreCriteria example) throws DataAccessException;

    CmsLayoutAttrPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutAttrPre record, @Param("example") CmsLayoutAttrPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutAttrPre record, @Param("example") CmsLayoutAttrPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutAttrPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutAttrPre record) throws DataAccessException;
}
