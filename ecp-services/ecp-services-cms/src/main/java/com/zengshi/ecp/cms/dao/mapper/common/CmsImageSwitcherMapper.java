package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsImageSwitcher;
import com.zengshi.ecp.cms.dao.model.CmsImageSwitcherCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsImageSwitcherMapper {
    Long countByExample(CmsImageSwitcherCriteria example) throws DataAccessException;

    int deleteByExample(CmsImageSwitcherCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsImageSwitcher record) throws DataAccessException;

    int insertSelective(CmsImageSwitcher record) throws DataAccessException;

    List<CmsImageSwitcher> selectByExample(CmsImageSwitcherCriteria example) throws DataAccessException;

    CmsImageSwitcher selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsImageSwitcher record, @Param("example") CmsImageSwitcherCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsImageSwitcher record, @Param("example") CmsImageSwitcherCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsImageSwitcher record) throws DataAccessException;

    int updateByPrimaryKey(CmsImageSwitcher record) throws DataAccessException;
}
