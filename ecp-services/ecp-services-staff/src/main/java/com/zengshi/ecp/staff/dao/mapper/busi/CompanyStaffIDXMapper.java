package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanyStaffIDX;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CompanyStaffIDXMapper {
    Long countByExample(CompanyStaffIDXCriteria example) throws DataAccessException;

    int deleteByExample(CompanyStaffIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long staffId) throws DataAccessException;

    int insert(CompanyStaffIDX record) throws DataAccessException;

    int insertSelective(CompanyStaffIDX record) throws DataAccessException;

    List<CompanyStaffIDX> selectByExample(CompanyStaffIDXCriteria example) throws DataAccessException;

    CompanyStaffIDX selectByPrimaryKey(Long staffId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CompanyStaffIDX record, @Param("example") CompanyStaffIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CompanyStaffIDX record, @Param("example") CompanyStaffIDXCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanyStaffIDX record) throws DataAccessException;

    int updateByPrimaryKey(CompanyStaffIDX record) throws DataAccessException;
}