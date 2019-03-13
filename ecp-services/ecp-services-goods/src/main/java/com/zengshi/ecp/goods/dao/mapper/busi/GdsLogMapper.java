package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsLog;
import com.zengshi.ecp.goods.dao.model.GdsLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsLogMapper {
    Long countByExample(GdsLogCriteria example) throws DataAccessException;

    int deleteByExample(GdsLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long logId) throws DataAccessException;

    int insert(GdsLog record) throws DataAccessException;

    int insertSelective(GdsLog record) throws DataAccessException;

    List<GdsLog> selectByExample(GdsLogCriteria example) throws DataAccessException;

    GdsLog selectByPrimaryKey(Long logId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsLog record, @Param("example") GdsLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsLog record, @Param("example") GdsLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsLog record) throws DataAccessException;

    int updateByPrimaryKey(GdsLog record) throws DataAccessException;
}
