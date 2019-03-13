package com.zengshi.ecp.staff.dao.mapper.common;

import com.zengshi.ecp.staff.dao.model.CustLevelInfo;
import com.zengshi.ecp.staff.dao.model.CustLevelInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustLevelInfoMapper {
    Long countByExample(CustLevelInfoCriteria example) throws DataAccessException;

    int deleteByExample(CustLevelInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String custLevelCode) throws DataAccessException;

    int insert(CustLevelInfo record) throws DataAccessException;

    int insertSelective(CustLevelInfo record) throws DataAccessException;

    List<CustLevelInfo> selectByExample(CustLevelInfoCriteria example) throws DataAccessException;

    CustLevelInfo selectByPrimaryKey(String custLevelCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustLevelInfo record, @Param("example") CustLevelInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustLevelInfo record, @Param("example") CustLevelInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustLevelInfo record) throws DataAccessException;

    int updateByPrimaryKey(CustLevelInfo record) throws DataAccessException;
}