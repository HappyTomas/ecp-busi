package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustEmailLog;
import com.zengshi.ecp.staff.dao.model.CustEmailLogCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CustEmailLogMapper {
    Long countByExample(CustEmailLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustEmailLog record) throws DataAccessException;

    int insertSelective(CustEmailLog record) throws DataAccessException;

    List<CustEmailLog> selectByExample(CustEmailLogCriteria example) throws DataAccessException;

    CustEmailLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(CustEmailLog record) throws DataAccessException;

    int updateByPrimaryKey(CustEmailLog record) throws DataAccessException;
}