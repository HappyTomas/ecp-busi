package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthStaff2GroupMapper {
    Long countByExample(AuthStaff2GroupCriteria example) throws DataAccessException;

    int deleteByExample(AuthStaff2GroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AuthStaff2GroupKey key) throws DataAccessException;

    int insert(AuthStaff2Group record) throws DataAccessException;

    int insertSelective(AuthStaff2Group record) throws DataAccessException;

    List<AuthStaff2Group> selectByExample(AuthStaff2GroupCriteria example) throws DataAccessException;

    AuthStaff2Group selectByPrimaryKey(AuthStaff2GroupKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthStaff2Group record, @Param("example") AuthStaff2GroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthStaff2Group record, @Param("example") AuthStaff2GroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaff2Group record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaff2Group record) throws DataAccessException;
}