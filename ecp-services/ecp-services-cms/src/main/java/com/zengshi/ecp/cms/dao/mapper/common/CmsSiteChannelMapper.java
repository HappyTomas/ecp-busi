package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsSiteChannel;
import com.zengshi.ecp.cms.dao.model.CmsSiteChannelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsSiteChannelMapper {
    Long countByExample(CmsSiteChannelCriteria example) throws DataAccessException;

    int deleteByExample(CmsSiteChannelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsSiteChannel record) throws DataAccessException;

    int insertSelective(CmsSiteChannel record) throws DataAccessException;

    List<CmsSiteChannel> selectByExample(CmsSiteChannelCriteria example) throws DataAccessException;

    CmsSiteChannel selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsSiteChannel record, @Param("example") CmsSiteChannelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsSiteChannel record, @Param("example") CmsSiteChannelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsSiteChannel record) throws DataAccessException;

    int updateByPrimaryKey(CmsSiteChannel record) throws DataAccessException;
}
