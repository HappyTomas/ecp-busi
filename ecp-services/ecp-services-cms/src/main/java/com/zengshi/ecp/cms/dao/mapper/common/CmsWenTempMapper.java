package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsWenTemp;
import com.zengshi.ecp.cms.dao.model.CmsWenTempCriteria;
import com.zengshi.ecp.cms.dao.model.CmsWenTempWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsWenTempMapper {
    Long countByExample(CmsWenTempCriteria example) throws DataAccessException;

    int deleteByExample(CmsWenTempCriteria example) throws DataAccessException;

    int insert(CmsWenTempWithBLOBs record) throws DataAccessException;

    int insertSelective(CmsWenTempWithBLOBs record) throws DataAccessException;

    List<CmsWenTempWithBLOBs> selectByExampleWithBLOBs(CmsWenTempCriteria example) throws DataAccessException;

    List<CmsWenTemp> selectByExample(CmsWenTempCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsWenTempWithBLOBs record, @Param("example") CmsWenTempCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") CmsWenTempWithBLOBs record, @Param("example") CmsWenTempCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsWenTemp record, @Param("example") CmsWenTempCriteria example) throws DataAccessException;
}
