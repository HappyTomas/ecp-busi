package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutPreMapper {
    Long countByExample(CmsLayoutPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutPre record) throws DataAccessException;

    int insertSelective(CmsLayoutPre record) throws DataAccessException;

    List<CmsLayoutPre> selectByExample(CmsLayoutPreCriteria example) throws DataAccessException;

    CmsLayoutPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutPre record, @Param("example") CmsLayoutPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutPre record, @Param("example") CmsLayoutPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutPre record) throws DataAccessException;
}
