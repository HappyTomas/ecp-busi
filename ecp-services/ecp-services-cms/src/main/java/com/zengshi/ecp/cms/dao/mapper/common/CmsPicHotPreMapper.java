package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPicHotPre;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPicHotPreMapper {
    Long countByExample(CmsPicHotPreCriteria example) throws DataAccessException;

    int deleteByExample(CmsPicHotPreCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPicHotPre record) throws DataAccessException;

    int insertSelective(CmsPicHotPre record) throws DataAccessException;

    List<CmsPicHotPre> selectByExample(CmsPicHotPreCriteria example) throws DataAccessException;

    CmsPicHotPre selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPicHotPre record, @Param("example") CmsPicHotPreCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPicHotPre record, @Param("example") CmsPicHotPreCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPicHotPre record) throws DataAccessException;

    int updateByPrimaryKey(CmsPicHotPre record) throws DataAccessException;
}
