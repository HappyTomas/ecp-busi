package com.zengshi.ecp.demo.dao.mapper.busi;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.DemoLogCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface DemoLogMapper {
    Long countByExample(DemoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long logId) throws DataAccessException;

    int insert(DemoLog record) throws DataAccessException;

    int insertSelective(DemoLog record) throws DataAccessException;

    List<DemoLog> selectByExample(DemoLogCriteria example) throws DataAccessException;

    DemoLog selectByPrimaryKey(Long logId) throws DataAccessException;

    int updateByPrimaryKeySelective(DemoLog record) throws DataAccessException;

    int updateByPrimaryKey(DemoLog record) throws DataAccessException;

    void insertBatch(List<DemoLog> recordLst) throws DataAccessException;
}
