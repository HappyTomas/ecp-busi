package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdminCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthStaffAdminMapper {
    Long countByExample(AuthStaffAdminCriteria example) throws DataAccessException;

    int deleteByExample(AuthStaffAdminCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthStaffAdmin record) throws DataAccessException;

    int insertSelective(AuthStaffAdmin record) throws DataAccessException;

    List<AuthStaffAdmin> selectByExample(AuthStaffAdminCriteria example) throws DataAccessException;

    AuthStaffAdmin selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthStaffAdmin record, @Param("example") AuthStaffAdminCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthStaffAdmin record, @Param("example") AuthStaffAdminCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaffAdmin record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaffAdmin record) throws DataAccessException;
}