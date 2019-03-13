package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface AuthStaffNIDXMapper {
    Long countByExample(AuthStaffNIDXCriteria example) throws DataAccessException;

    int deleteByExample(AuthStaffNIDXCriteria example) throws DataAccessException;

    int insert(AuthStaffNIDX record) throws DataAccessException;

    int insertSelective(AuthStaffNIDX record) throws DataAccessException;

    List<AuthStaffNIDX> selectByExample(AuthStaffNIDXCriteria example) throws DataAccessException;
}