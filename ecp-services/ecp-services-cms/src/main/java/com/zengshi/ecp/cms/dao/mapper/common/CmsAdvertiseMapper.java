package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsAdvertise;
import com.zengshi.ecp.cms.dao.model.CmsAdvertiseCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsAdvertiseMapper {
    Long countByExample(CmsAdvertiseCriteria example) throws DataAccessException;

    int deleteByExample(CmsAdvertiseCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsAdvertise record) throws DataAccessException;

    int insertSelective(CmsAdvertise record) throws DataAccessException;

    List<CmsAdvertise> selectByExample(CmsAdvertiseCriteria example) throws DataAccessException;

    CmsAdvertise selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsAdvertise record, @Param("example") CmsAdvertiseCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsAdvertise record, @Param("example") CmsAdvertiseCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsAdvertise record) throws DataAccessException;

    int updateByPrimaryKey(CmsAdvertise record) throws DataAccessException;
}
