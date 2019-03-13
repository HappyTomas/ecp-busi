package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustSensitiveLog;
import com.zengshi.ecp.staff.dao.model.CustSensitiveLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustSensitiveLogMapper {
    Long countByExample(CustSensitiveLogCriteria example) throws DataAccessException;

    int deleteByExample(CustSensitiveLogCriteria example) throws DataAccessException;

    int insert(CustSensitiveLog record) throws DataAccessException;

    int insertSelective(CustSensitiveLog record) throws DataAccessException;

    List<CustSensitiveLog> selectByExample(CustSensitiveLogCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustSensitiveLog record, @Param("example") CustSensitiveLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustSensitiveLog record, @Param("example") CustSensitiveLogCriteria example) throws DataAccessException;
}