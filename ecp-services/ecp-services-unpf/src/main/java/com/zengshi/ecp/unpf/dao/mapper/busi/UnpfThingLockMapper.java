package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfThingLock;
import com.zengshi.ecp.unpf.dao.model.UnpfThingLockCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfThingLockMapper {
    Long countByExample(UnpfThingLockCriteria example) throws DataAccessException;

    int deleteByExample(UnpfThingLockCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(UnpfThingLock record) throws DataAccessException;

    int insertSelective(UnpfThingLock record) throws DataAccessException;

    List<UnpfThingLock> selectByExample(UnpfThingLockCriteria example) throws DataAccessException;

    UnpfThingLock selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfThingLock record, @Param("example") UnpfThingLockCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfThingLock record, @Param("example") UnpfThingLockCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfThingLock record) throws DataAccessException;

    int updateByPrimaryKey(UnpfThingLock record) throws DataAccessException;
}