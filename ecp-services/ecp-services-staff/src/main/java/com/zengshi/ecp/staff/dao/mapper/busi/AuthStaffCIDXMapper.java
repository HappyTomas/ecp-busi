package com.zengshi.ecp.staff.dao.mapper.busi;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;

public interface AuthStaffCIDXMapper {
    Long countByExample(AuthStaffCIDXCriteria example) throws DataAccessException;

    int insert(AuthStaffCIDX record) throws DataAccessException;

    int insertSelective(AuthStaffCIDX record) throws DataAccessException;

    List<AuthStaffCIDX> selectByExample(AuthStaffCIDXCriteria example) throws DataAccessException;

    void insertBatch(List<AuthStaffCIDX> recordLst) throws DataAccessException;
    
    int updateSerialNumberByCode(AuthStaffCIDX record) throws DataAccessException;
}