package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthStaffGroupMapper {
    Long countByExample(AuthStaffGroupCriteria example) throws DataAccessException;

    int deleteByExample(AuthStaffGroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AuthStaffGroup record) throws DataAccessException;

    int insertSelective(AuthStaffGroup record) throws DataAccessException;

    List<AuthStaffGroup> selectByExample(AuthStaffGroupCriteria example) throws DataAccessException;

    AuthStaffGroup selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AuthStaffGroup record, @Param("example") AuthStaffGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AuthStaffGroup record, @Param("example") AuthStaffGroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthStaffGroup record) throws DataAccessException;

    int updateByPrimaryKey(AuthStaffGroup record) throws DataAccessException;
}