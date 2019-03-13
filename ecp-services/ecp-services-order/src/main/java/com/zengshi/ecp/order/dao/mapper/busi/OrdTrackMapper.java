package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dao.model.OrdTrackCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdTrackMapper {
    Long countByExample(OrdTrackCriteria example) throws DataAccessException;

    int deleteByExample(OrdTrackCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdTrack record) throws DataAccessException;

    int insertSelective(OrdTrack record) throws DataAccessException;

    List<OrdTrack> selectByExample(OrdTrackCriteria example) throws DataAccessException;

    OrdTrack selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdTrack record, @Param("example") OrdTrackCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdTrack record, @Param("example") OrdTrackCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdTrack record) throws DataAccessException;

    int updateByPrimaryKey(OrdTrack record) throws DataAccessException;
}