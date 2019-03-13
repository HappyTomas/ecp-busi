package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctInfoTemp;
import com.zengshi.ecp.staff.dao.model.AcctInfoTempCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctInfoTempMapper {
    Long countByExample(AcctInfoTempCriteria example) throws DataAccessException;

    int deleteByExample(AcctInfoTempCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctInfoTemp record) throws DataAccessException;

    int insertSelective(AcctInfoTemp record) throws DataAccessException;

    List<AcctInfoTemp> selectByExample(AcctInfoTempCriteria example) throws DataAccessException;

    AcctInfoTemp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctInfoTemp record, @Param("example") AcctInfoTempCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctInfoTemp record, @Param("example") AcctInfoTempCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctInfoTemp record) throws DataAccessException;

    int updateByPrimaryKey(AcctInfoTemp record) throws DataAccessException;
}