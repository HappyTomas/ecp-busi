package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsChannel;
import com.zengshi.ecp.cms.dao.model.CmsChannelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsChannelMapper {
    Long countByExample(CmsChannelCriteria example) throws DataAccessException;

    int deleteByExample(CmsChannelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsChannel record) throws DataAccessException;

    int insertSelective(CmsChannel record) throws DataAccessException;

    List<CmsChannel> selectByExample(CmsChannelCriteria example) throws DataAccessException;

    CmsChannel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsChannel record, @Param("example") CmsChannelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsChannel record, @Param("example") CmsChannelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsChannel record) throws DataAccessException;

    int updateByPrimaryKey(CmsChannel record) throws DataAccessException;
}
