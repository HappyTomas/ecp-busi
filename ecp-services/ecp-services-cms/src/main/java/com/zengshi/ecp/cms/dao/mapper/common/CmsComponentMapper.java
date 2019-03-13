package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsComponent;
import com.zengshi.ecp.cms.dao.model.CmsComponentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsComponentMapper {
    Long countByExample(CmsComponentCriteria example) throws DataAccessException;

    int deleteByExample(CmsComponentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsComponent record) throws DataAccessException;

    int insertSelective(CmsComponent record) throws DataAccessException;

    List<CmsComponent> selectByExample(CmsComponentCriteria example) throws DataAccessException;

    CmsComponent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsComponent record, @Param("example") CmsComponentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsComponent record, @Param("example") CmsComponentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsComponent record) throws DataAccessException;

    int updateByPrimaryKey(CmsComponent record) throws DataAccessException;
}
