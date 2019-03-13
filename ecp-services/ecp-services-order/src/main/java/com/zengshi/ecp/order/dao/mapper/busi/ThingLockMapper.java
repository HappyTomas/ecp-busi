package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.ThingLock;
import com.zengshi.ecp.order.dao.model.ThingLockCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ThingLockMapper {
    Long countByExample(ThingLockCriteria example) throws DataAccessException;

    int deleteByExample(ThingLockCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(ThingLock record) throws DataAccessException;

    int insertSelective(ThingLock record) throws DataAccessException;

    List<ThingLock> selectByExample(ThingLockCriteria example) throws DataAccessException;

    ThingLock selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ThingLock record, @Param("example") ThingLockCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ThingLock record, @Param("example") ThingLockCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ThingLock record) throws DataAccessException;

    int updateByPrimaryKey(ThingLock record) throws DataAccessException;
}