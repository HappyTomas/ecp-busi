package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsLink;
import com.zengshi.ecp.cms.dao.model.CmsLinkCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsLinkMapper {
    Long countByExample(CmsLinkCriteria example) throws DataAccessException;

    int deleteByExample(CmsLinkCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsLink record) throws DataAccessException;

    int insertSelective(CmsLink record) throws DataAccessException;

    List<CmsLink> selectByExample(CmsLinkCriteria example) throws DataAccessException;

    CmsLink selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsLink record, @Param("example") CmsLinkCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsLink record, @Param("example") CmsLinkCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsLink record) throws DataAccessException;

    int updateByPrimaryKey(CmsLink record) throws DataAccessException;
}
