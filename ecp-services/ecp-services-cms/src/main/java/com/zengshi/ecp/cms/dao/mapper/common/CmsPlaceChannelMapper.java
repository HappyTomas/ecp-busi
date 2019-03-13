package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsPlaceChannel;
import com.zengshi.ecp.cms.dao.model.CmsPlaceChannelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsPlaceChannelMapper {
    Long countByExample(CmsPlaceChannelCriteria example) throws DataAccessException;

    int deleteByExample(CmsPlaceChannelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsPlaceChannel record) throws DataAccessException;

    int insertSelective(CmsPlaceChannel record) throws DataAccessException;

    List<CmsPlaceChannel> selectByExample(CmsPlaceChannelCriteria example) throws DataAccessException;

    CmsPlaceChannel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsPlaceChannel record, @Param("example") CmsPlaceChannelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsPlaceChannel record, @Param("example") CmsPlaceChannelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsPlaceChannel record) throws DataAccessException;

    int updateByPrimaryKey(CmsPlaceChannel record) throws DataAccessException;
}
