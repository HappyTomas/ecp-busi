package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanyNameIDX;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CompanyNameIDXMapper {
    Long countByExample(CompanyNameIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long companyId) throws DataAccessException;

    int insert(CompanyNameIDX record) throws DataAccessException;

    int insertSelective(CompanyNameIDX record) throws DataAccessException;

    List<CompanyNameIDX> selectByExample(CompanyNameIDXCriteria example) throws DataAccessException;

    CompanyNameIDX selectByPrimaryKey(Long companyId) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanyNameIDX record) throws DataAccessException;

    int updateByPrimaryKey(CompanyNameIDX record) throws DataAccessException;

    void insertBatch(List<CompanyNameIDX> recordLst) throws DataAccessException;
}