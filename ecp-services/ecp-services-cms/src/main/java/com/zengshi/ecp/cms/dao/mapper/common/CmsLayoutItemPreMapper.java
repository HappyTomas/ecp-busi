package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutItemPreMapper {
    Long countByExample(CmsLayoutItemPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutItemPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutItemPre record) throws DataAccessException;

    int insertSelective(CmsLayoutItemPre record) throws DataAccessException;

    List<CmsLayoutItemPre> selectByExample(CmsLayoutItemPreCriteria example) throws DataAccessException;

    CmsLayoutItemPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutItemPre record, @Param("example") CmsLayoutItemPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutItemPre record, @Param("example") CmsLayoutItemPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutItemPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutItemPre record) throws DataAccessException;
}
