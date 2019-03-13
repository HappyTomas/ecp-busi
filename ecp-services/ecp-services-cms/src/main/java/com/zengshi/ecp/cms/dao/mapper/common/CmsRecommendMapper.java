package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsRecommend;
import com.zengshi.ecp.cms.dao.model.CmsRecommendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsRecommendMapper {
    Long countByExample(CmsRecommendCriteria example) throws DataAccessException;

    int deleteByExample(CmsRecommendCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsRecommend record) throws DataAccessException;

    int insertSelective(CmsRecommend record) throws DataAccessException;

    List<CmsRecommend> selectByExample(CmsRecommendCriteria example) throws DataAccessException;

    CmsRecommend selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsRecommend record, @Param("example") CmsRecommendCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsRecommend record, @Param("example") CmsRecommendCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsRecommend record) throws DataAccessException;

    int updateByPrimaryKey(CmsRecommend record) throws DataAccessException;
}
