package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPicHotPub;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPicHotPubMapper {
    Long countByExample(CmsPicHotPubCriteria example) throws DataAccessException;

    int deleteByExample(CmsPicHotPubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPicHotPub record) throws DataAccessException;

    int insertSelective(CmsPicHotPub record) throws DataAccessException;

    List<CmsPicHotPub> selectByExample(CmsPicHotPubCriteria example) throws DataAccessException;

    CmsPicHotPub selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPicHotPub record, @Param("example") CmsPicHotPubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPicHotPub record, @Param("example") CmsPicHotPubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPicHotPub record) throws DataAccessException;

    int updateByPrimaryKey(CmsPicHotPub record) throws DataAccessException;
}
