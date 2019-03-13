package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.AppVersion;
import com.zengshi.ecp.sys.dao.model.AppVersionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AppVersionMapper {
    Long countByExample(AppVersionCriteria example) throws DataAccessException;

    int deleteByExample(AppVersionCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AppVersion record) throws DataAccessException;

    int insertSelective(AppVersion record) throws DataAccessException;

    List<AppVersion> selectByExample(AppVersionCriteria example) throws DataAccessException;

    AppVersion selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AppVersion record, @Param("example") AppVersionCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AppVersion record, @Param("example") AppVersionCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AppVersion record) throws DataAccessException;

    int updateByPrimaryKey(AppVersion record) throws DataAccessException;
}
