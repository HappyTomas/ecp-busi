package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsHotSearch;
import com.zengshi.ecp.cms.dao.model.CmsHotSearchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsHotSearchMapper {
    Long countByExample(CmsHotSearchCriteria example) throws DataAccessException;

    int deleteByExample(CmsHotSearchCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsHotSearch record) throws DataAccessException;

    int insertSelective(CmsHotSearch record) throws DataAccessException;

    List<CmsHotSearch> selectByExample(CmsHotSearchCriteria example) throws DataAccessException;

    CmsHotSearch selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsHotSearch record, @Param("example") CmsHotSearchCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsHotSearch record, @Param("example") CmsHotSearchCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsHotSearch record) throws DataAccessException;

    int updateByPrimaryKey(CmsHotSearch record) throws DataAccessException;
}
