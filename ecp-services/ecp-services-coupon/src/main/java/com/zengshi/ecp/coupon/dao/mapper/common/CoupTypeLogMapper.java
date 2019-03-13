package com.zengshi.ecp.coupon.dao.mapper.common;

import com.zengshi.ecp.coupon.dao.model.CoupTypeLog;
import com.zengshi.ecp.coupon.dao.model.CoupTypeLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupTypeLogMapper {
    Long countByExample(CoupTypeLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupTypeLog record) throws DataAccessException;

    int insertSelective(CoupTypeLog record) throws DataAccessException;

    List<CoupTypeLog> selectByExample(CoupTypeLogCriteria example) throws DataAccessException;

    CoupTypeLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupTypeLog record, @Param("example") CoupTypeLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupTypeLog record, @Param("example") CoupTypeLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupTypeLog record) throws DataAccessException;

    int updateByPrimaryKey(CoupTypeLog record) throws DataAccessException;
}
