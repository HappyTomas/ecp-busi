package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLayoutType;
import com.zengshi.ecp.cms.dao.model.CmsLayoutTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLayoutTypeMapper {
    Long countByExample(CmsLayoutTypeCriteria example) throws DataAccessException;

    int deleteByExample(CmsLayoutTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLayoutType record) throws DataAccessException;

    int insertSelective(CmsLayoutType record) throws DataAccessException;

    List<CmsLayoutType> selectByExample(CmsLayoutTypeCriteria example) throws DataAccessException;

    CmsLayoutType selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLayoutType record, @Param("example") CmsLayoutTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLayoutType record, @Param("example") CmsLayoutTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLayoutType record) throws DataAccessException;

    int updateByPrimaryKey(CmsLayoutType record) throws DataAccessException;
}
