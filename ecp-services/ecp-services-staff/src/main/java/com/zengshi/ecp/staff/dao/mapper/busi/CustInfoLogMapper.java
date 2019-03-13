package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustInfoLog;
import com.zengshi.ecp.staff.dao.model.CustInfoLogCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CustInfoLogMapper {
    Long countByExample(CustInfoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustInfoLog record) throws DataAccessException;

    int insertSelective(CustInfoLog record) throws DataAccessException;

    List<CustInfoLog> selectByExample(CustInfoLogCriteria example) throws DataAccessException;

    CustInfoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(CustInfoLog record) throws DataAccessException;

    int updateByPrimaryKey(CustInfoLog record) throws DataAccessException;
}