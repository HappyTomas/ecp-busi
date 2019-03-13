package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsSiteInfo;
import com.zengshi.ecp.cms.dao.model.CmsSiteInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsSiteInfoMapper {
    Long countByExample(CmsSiteInfoCriteria example) throws DataAccessException;

    int deleteByExample(CmsSiteInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsSiteInfo record) throws DataAccessException;

    int insertSelective(CmsSiteInfo record) throws DataAccessException;

    List<CmsSiteInfo> selectByExample(CmsSiteInfoCriteria example) throws DataAccessException;

    CmsSiteInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsSiteInfo record, @Param("example") CmsSiteInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsSiteInfo record, @Param("example") CmsSiteInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsSiteInfo record) throws DataAccessException;

    int updateByPrimaryKey(CmsSiteInfo record) throws DataAccessException;
}
