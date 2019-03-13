package com.zengshi.ecp.staff.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;

public interface AuthStaffCIDXExtMapper {
    
    int deleteByStaffCode(AuthStaffCIDX record) throws DataAccessException;
}