package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyInfoCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CompanyInfoMapper {
    Long countByExample(CompanyInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CompanyInfo record) throws DataAccessException;

    int insertSelective(CompanyInfo record) throws DataAccessException;

    List<CompanyInfo> selectByExample(CompanyInfoCriteria example) throws DataAccessException;

    CompanyInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanyInfo record) throws DataAccessException;

    int updateByPrimaryKey(CompanyInfo record) throws DataAccessException;

    void insertBatch(List<CompanyInfo> recordLst) throws DataAccessException;
}