package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanyInfoLog;
import com.zengshi.ecp.staff.dao.model.CompanyInfoLogCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CompanyInfoLogMapper {
    Long countByExample(CompanyInfoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CompanyInfoLog record) throws DataAccessException;

    int insertSelective(CompanyInfoLog record) throws DataAccessException;

    List<CompanyInfoLog> selectByExample(CompanyInfoLogCriteria example) throws DataAccessException;

    CompanyInfoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanyInfoLog record) throws DataAccessException;

    int updateByPrimaryKey(CompanyInfoLog record) throws DataAccessException;
}