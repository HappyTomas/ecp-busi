package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaffEIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDXCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface AuthStaffEIDXMapper {
    Long countByExample(AuthStaffEIDXCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String email) throws DataAccessException;

    int insert(AuthStaffEIDX record) throws DataAccessException;

    int insertSelective(AuthStaffEIDX record) throws DataAccessException;

    List<AuthStaffEIDX> selectByExample(AuthStaffEIDXCriteria example) throws DataAccessException;

    AuthStaffEIDX selectByPrimaryKey(String email) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaffEIDX record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaffEIDX record) throws DataAccessException;
}