package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthAdminCIDX;
import com.zengshi.ecp.staff.dao.model.AuthAdminCIDXCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthAdminCIDXMapper {
    Long countByExample(AuthAdminCIDXCriteria example) throws DataAccessException;

    int deleteByExample(AuthAdminCIDXCriteria example) throws DataAccessException;

    int insert(AuthAdminCIDX record) throws DataAccessException;

    int insertSelective(AuthAdminCIDX record) throws DataAccessException;

    List<AuthAdminCIDX> selectByExample(AuthAdminCIDXCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthAdminCIDX record, @Param("example") AuthAdminCIDXCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthAdminCIDX record, @Param("example") AuthAdminCIDXCriteria example) throws DataAccessException;
}