package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdOffline;
import com.zengshi.ecp.order.dao.model.OrdOfflineCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdOfflineMapper {
    Long countByExample(OrdOfflineCriteria example) throws DataAccessException;

    int deleteByExample(OrdOfflineCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdOffline record) throws DataAccessException;

    int insertSelective(OrdOffline record) throws DataAccessException;

    List<OrdOffline> selectByExample(OrdOfflineCriteria example) throws DataAccessException;

    OrdOffline selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdOffline record, @Param("example") OrdOfflineCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdOffline record, @Param("example") OrdOfflineCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdOffline record) throws DataAccessException;

    int updateByPrimaryKey(OrdOffline record) throws DataAccessException;
}