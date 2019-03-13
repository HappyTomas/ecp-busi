package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsInfo;
import com.zengshi.ecp.cms.dao.model.CmsInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsInfoMapper {
    Long countByExample(CmsInfoCriteria example) throws DataAccessException;

    int deleteByExample(CmsInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsInfo record) throws DataAccessException;

    int insertSelective(CmsInfo record) throws DataAccessException;

    List<CmsInfo> selectByExample(CmsInfoCriteria example) throws DataAccessException;

    CmsInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsInfo record, @Param("example") CmsInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsInfo record, @Param("example") CmsInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsInfo record) throws DataAccessException;

    int updateByPrimaryKey(CmsInfo record) throws DataAccessException;
}
