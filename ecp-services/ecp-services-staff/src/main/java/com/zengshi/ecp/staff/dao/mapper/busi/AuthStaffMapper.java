package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaffCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface AuthStaffMapper {
    Long countByExample(AuthStaffCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthStaff record) throws DataAccessException;

    int insertSelective(AuthStaff record) throws DataAccessException;

    List<AuthStaff> selectByExample(AuthStaffCriteria example) throws DataAccessException;

    AuthStaff selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaff record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaff record) throws DataAccessException;

    void insertBatch(List<AuthStaff> recordLst) throws DataAccessException;
}