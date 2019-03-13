package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackTrack;
import com.zengshi.ecp.order.dao.model.OrdBackTrackCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackTrackMapper {
    Long countByExample(OrdBackTrackCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackTrackCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackTrack record) throws DataAccessException;

    int insertSelective(OrdBackTrack record) throws DataAccessException;

    List<OrdBackTrack> selectByExample(OrdBackTrackCriteria example) throws DataAccessException;

    OrdBackTrack selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackTrack record, @Param("example") OrdBackTrackCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackTrack record, @Param("example") OrdBackTrackCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackTrack record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackTrack record) throws DataAccessException;
}