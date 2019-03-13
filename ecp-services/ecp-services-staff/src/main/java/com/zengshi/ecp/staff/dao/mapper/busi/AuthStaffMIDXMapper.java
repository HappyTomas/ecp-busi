package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDXCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface AuthStaffMIDXMapper {
    Long countByExample(AuthStaffMIDXCriteria example) throws DataAccessException;

    int insert(AuthStaffMIDX record) throws DataAccessException;

    int insertSelective(AuthStaffMIDX record) throws DataAccessException;

    List<AuthStaffMIDX> selectByExample(AuthStaffMIDXCriteria example) throws DataAccessException;

    void insertBatch(List<AuthStaffMIDX> recordLst) throws DataAccessException;
    
    int deleteByPrimaryKey(String serialNumber) throws DataAccessException;
}